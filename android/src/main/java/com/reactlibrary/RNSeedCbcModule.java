
package com.reactlibrary;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.ReactConstants;
import java.util.Map;
import org.kisa.*;
 

public class RNSeedCbcModule extends ReactContextBaseJavaModule {
  
 static {
		// JNI Library Load
		System.loadLibrary("KISACrypto");
	};

  private final ReactApplicationContext reactContext;
  
  public static final byte[] key = {
  0x01, 0x02, 0x03, 0x04,
  0x01, 0x02, 0x03, 0x04,
  0x01, 0x02, 0x03, 0x04,
  0x01, 0x02, 0x03, 0x04,
  };

  public static final byte[] iv = {
  0x01, 0x02, 0x03, 0x04,
  0x01, 0x02, 0x03, 0x04,
  0x01, 0x02, 0x03, 0x04,
  0x01, 0x02, 0x03, 0x04,
  };
  
  public RNSeedCbcModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNSeedCbc";
  }

  @ReactMethod
  public static void encryption( String strText, Promise promise) {
  byte[] plainText = strText.getBytes();
  byte[] cipherText = new byte[plainText.length + 16];
  int outputTextLen = 0;
  WritableMap map = Arguments.createMap();
  SEEDCBC seed = new SEEDCBC();
  seed.init(SEEDCBC.ENC, key, iv);
  outputTextLen = seed.process(
  plainText, 0,
  plainText.length,
  cipherText, 0);

  seed.close(cipherText, outputTextLen);
  String hexText = new java.math.BigInteger(cipherText).toString(16);
  strText = new String(hexText);
  map.putString("strText", strText.trim());
  promise.resolve(map);
  Log.d("test", strText.trim());
//   return strText.trim();
  
}

  public static String decryption(String strText) {
  if(strText == null || strText.equals("")) return "";

byte[] cipherText = new java.math.BigInteger(strText.trim(), 16).toByteArray();
byte[] plainText = new byte[144];
int outputTextLen = 0;

SEEDCBC seed = new SEEDCBC();
seed.init(SEEDCBC.DEC, key, iv);

outputTextLen = seed.process(
cipherText, 0,
cipherText.length,
plainText, 0);

seed.close(plainText, outputTextLen);

strText = new String(plainText).trim();

return strText;
}




}



