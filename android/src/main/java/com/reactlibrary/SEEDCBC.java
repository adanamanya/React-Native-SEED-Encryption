package com.reactlibrary;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SEEDCBC
{
  public static final int SEED_BLOCK_SIZE = 16;
  public static final int ENC = 1;
  public static final int DEC = 0;
  private int encrypt;
  private byte[] ivec;
  private int[] seedKey;
  private byte[] cbc_buffer;
  private int[] buffer_length;
  private byte[] cbc_last_block;
  private int[] last_block_flag;
  
  public SEEDCBC()
  {
    this.ivec = new byte[16];
    this.seedKey = new int[32];
    this.cbc_buffer = new byte[16];
    this.buffer_length = new int[1];
    this.cbc_last_block = new byte[16];
    this.last_block_flag = new int[1];
  }
  
  public static native int seedCBCInit(byte[] paramArrayOfByte, int[] paramArrayOfInt);
  
  private native int internalSeedCBCProcessEnc(int paramInt1, int[] paramArrayOfInt1, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int[] paramArrayOfInt2, byte[] paramArrayOfByte3, int paramInt2, int paramInt3, byte[] paramArrayOfByte4, int paramInt4);
  
  private native int internalSeedCBCProcessDec(int paramInt1, int[] paramArrayOfInt1, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int[] paramArrayOfInt2, byte[] paramArrayOfByte3, int[] paramArrayOfInt3, byte[] paramArrayOfByte4, int paramInt2, int paramInt3, byte[] paramArrayOfByte5, int paramInt4);
  
  private native int internalSeedProcessBlocks(int[] paramArrayOfInt, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, int paramInt);
  
  public static native void encryptBlock(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt, int[] paramArrayOfInt);
  
  public int init(int enc, byte[] key, byte[] iv)
  {
    if ((key == null) || (iv == null)) {
      return 0;
    }
    if (seedCBCInit(key, this.seedKey) == -1) {
      return 0;
    }
    System.arraycopy(iv, 0, this.ivec, 0, 16);
    this.encrypt = enc;
    this.last_block_flag[0] = (this.buffer_length[0] = 0);
    
    return 1;
  }
  
  public int process(byte[] inputText, int inputOffset, int inputTextLen, byte[] outputText, int outputOffset)
  {
    if ((inputText == null) || (outputText == null)) {
      return 0;
    }
    if (inputTextLen <= 0) {
      return -1;
    }
    if ((inputOffset < 0) || (inputOffset >= inputText.length) || (outputOffset < 0) || (outputOffset >= outputText.length)) {
      return -1;
    }
    int outputTextLen = 0;
    if (this.encrypt == 1)
    {
      outputTextLen = internalSeedCBCProcessEnc(this.encrypt, this.seedKey, this.ivec, this.cbc_buffer, this.buffer_length, inputText, inputOffset, inputTextLen, outputText, outputOffset);
      
      return outputTextLen;
    }
    outputTextLen = internalSeedCBCProcessDec(this.encrypt, this.seedKey, this.ivec, this.cbc_buffer, this.buffer_length, this.cbc_last_block, this.last_block_flag, inputText, inputOffset, inputTextLen, outputText, outputOffset);
    
    return outputTextLen;
  }
  
  public int close(byte[] outputText, int outputTextLen)
  {
    if (outputText == null) {
      return 0;
    }
    if (this.encrypt == 1)
    {
      int padLen = 16 - this.buffer_length[0];
      for (int i = this.buffer_length[0]; i < 16; i++) {
        this.cbc_buffer[i] = ((byte)padLen);
      }
      if (internalSeedProcessBlocks(this.seedKey, this.ivec, this.cbc_buffer, outputText, outputTextLen) == -1) {
        return -1;
      }
      outputTextLen = 16;
    }
    else
    {
      int padLen = 16 - this.cbc_last_block[15];
      if (padLen > 16) {
        return -1;
      }
      if (padLen > 1)
      {
        int i = this.cbc_last_block[15];
        while (i > 0)
        {
          if (this.cbc_last_block[15] != this.cbc_last_block[(16 - i)]) {
            return -1;
          }
          i--;
        }
      }
      for (int i = 0; i < padLen; i++) {
        outputText[(outputTextLen + i)] = this.cbc_last_block[i];
      }
      outputTextLen = padLen;
    }
    return outputTextLen;
  }
  
  public int CBC_ENCRYPT(byte[] user_key, byte[] iv, byte[] inputText, int inputOffset, int inputTextLen, byte[] outputText, int outputOffset)
  {
    int padLen = 0;
    int outputTextLen = 0;
    if (init(1, user_key, iv) == 0) {
      return 0;
    }
    outputTextLen = process(inputText, inputOffset, inputTextLen, outputText, outputOffset);
    if (outputTextLen < 0) {
      return 0;
    }
    padLen = close(outputText, outputTextLen);
    if (padLen < 0) {
      return 0;
    }
    return outputTextLen + padLen;
  }
  
  public int CBC_DECRYPT(byte[] user_key, byte[] iv, byte[] inputText, int inputOffset, int inputTextLen, byte[] outputText, int outputOffset)
  {
    int padLen = 0;
    int outputTextLen = 0;
    if (init(0, user_key, iv) == 0) {
      return 0;
    }
    outputTextLen = process(inputText, inputOffset, inputTextLen, outputText, outputOffset);
    if (outputTextLen < 0) {
      return 0;
    }
    padLen = close(outputText, outputTextLen);
    if (padLen < 0) {
      return 0;
    }
    return outputTextLen + padLen;
  }
  
  public int getOutputSize(int inputLen)
  {
    return getOutputSize(this.encrypt, inputLen);
  }
  
  public int getOutputSize(int enc, int inputLen)
  {
    int outputLen = 0;
    if (enc == 1)
    {
      int padLen = 16 - inputLen % 16;
      if (padLen == 16) {
        outputLen = inputLen + 16;
      } else {
        outputLen = inputLen + padLen;
      }
    }
    else
    {
      outputLen = inputLen;
    }
    return outputLen;
  }
}
