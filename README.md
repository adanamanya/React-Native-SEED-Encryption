
# react-native-seed-cbc WIP

React Native Plugin to encrypt string based on KISA SEED-CBC(Korea Internet & Security Agency) standarization

## Getting started

`$ npm install react-native-seed-cbc --save`

### Mostly automatic installation

`$ react-native link react-native-seed-cbc`


### Manual installation

### Link .jar library with android studio

1. Open your project/android folder in android studio
2. Open android/libs folder 
3. Select KISACrypto.jar = > Build => Edit Libraries &      Dependencies
4. add new library => choose KISACrypto.jar
5. build

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNSeedCbcPackage;` to the imports at the top of the file
  - Add `new RNSeedCbcPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-seed-cbc'
  	project(':react-native-seed-cbc').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-seed-cbc/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-seed-cbc')
  	```


## Usage
```javascript
import RNSeedCbc from 'react-native-seed-cbc';

// encrypt string
RNSeedCbc.encryption("Place your string here")
// decrypt string
RNSeedCbc.decryption(your variable)
```
## Example
```javascript
  export default class App extends Component{
  static contextTypes = {
    rootTag: PropTypes.number,
  };
  componentDidMount() {
   
  }

  async asd(){
    const strText = await RNSeedCbc.encryption("asdefgafgakakadakgh");
    console.log(strText);
  }
  async def(){
    const dcrText = await RNSeedCbc.decryption(strText.toString());
    console.log(dcrText);
  }

  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>
          Welcome to React Native! 
        </Text>
        <Button
        onPress={() => this.asd()}
        title="Learn More"
        color="#841584"
        accessibilityLabel="Learn more about this purple button"
        />
        <Button
        onPress={() => this.def()}
        title="decrypt"
        color="#841584"
        accessibilityLabel="Learn more about this purple button"
        />
        <Text style={styles.instructions}>
          {instructions}
        </Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
```