
# react-native-seed-cbc

## Getting started

`$ npm install react-native-seed-cbc --save`

### Mostly automatic installation

`$ react-native link react-native-seed-cbc`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-seed-cbc` and add `RNSeedCbc.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNSeedCbc.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

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

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNSeedCbc.sln` in `node_modules/react-native-seed-cbc/windows/RNSeedCbc.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Seed.Cbc.RNSeedCbc;` to the usings at the top of the file
  - Add `new RNSeedCbcPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNSeedCbc from 'react-native-seed-cbc';

// TODO: What to do with the module?
RNSeedCbc;
```
  