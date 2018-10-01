### Readme Carousell Automation Setup


Project to Automate testing for Android.

Setup
=================

##### 1. Clone from your forked Platform Automation repo.

##### 2. Download JDK 

Download JDK(Version 1.7 and above) and Android SDK in the local machine.

2.1 Goto Java SE download site @ http://www.oracle.com/technetwork/java/javase/downloads/index.html. Under "Java Platform, Standard Edition" ⇒ “Java SE 8u181" ⇒ Click the JDK's "Download" button

2.2. Check "Accept License Agreement"

2.3. Choose your operating platform, i.e., MacOS. Download the installer (e.g, jdk-8 dmg - 395MB)

##### 3. Download Android SDK - Where the Android-SDK is installed depends on how you installed it

3.1. If you downloaded the SDK through website and then dragged/dropped the Application to your Applications folder, it's most likely here:  /Applications/ADT/sdk (as it is in your case). 

3.2. If you installed the SDK using Homebrew (brew cask install android-sdk), then it's located here: /usr/local/Caskroom/android-sdk/{YOUR_SDK_VERSION_NUMBER} 
More info: https://www.howtogeek.com/211541/homebrew-for-os-x-easily-installs-desktop-apps-and-terminal-utilities/

3.3. If the SDK was installed automatically as part of Android Studio then it's located here: /Users/{YOUR_USER_NAME}/Library/Android/sdk 
Once you know the location, open a terminal window and enter the following (changing out the path to the SDK to be however you installed it):

##### 4. Updating Bash Profile

4.1 export JAVA_HOME={YOUR_PATH}  & ANDROID_HOME={YOUR_PATH}
Once you have this set, you need to add this to the PATH environment variable

4.2 export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools

4.3 source ~/.bash_profile
Lastly apply these changes by re-sourcing .bash_profile:

SAMPLE Bash Profile 

```bash
export ANDROID_HOME=/Users/sanjit/Library/Android/sdk
export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools:$ANDROID_HOME/build-tools/22.0.1
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home
export PATH=$PATH:$JAVA_HOME/bin
```

##### 5. [Optional] Install an IDE. We recommend IntelliJ IDEA, but feel free to use any Java IDE.


##### 6.  Install Node and appium

```bash
 brew install node      # get node.js
 npm install -g appium  # get appium
 npm install wd         # get appium client
 appium &               # start appium 
```

##### 7. Install Maven - OS X prior to Mavericks (10.9) actually comes with Maven 3 built in.

```bash 
brew install maven
mvn -version
```
##### 8. Importing existing project to IntelliJ IDE (File > New > Project from existing sources)  select import project from external mode and choose Maven and complete till finish

##### 9. Cucumber for Java, Gherkin Plugin Needs to be updated (IDE will handle this, you just need to install plugin) Note: don’t install Substeps IntelliJ Plugin 

##### 10. Minimum one (Android) device needs to be connected to the system

##### 11. Executing ProductListing.feature

```bash
mvn clean test -Dtest=JunitRunner -Dcucumber.options="src/main/resources/features/ --tags @carousell --plugin html:target/site/cucumber-pretty@2.5.1 --plugin json:target/result.json"
```
##### 11. Reports can be found at target/report.html (Open in Browser)

##### 12. Basic report - target/site/cucumber-pretty@2.5.1/index.html (Open in browser)

