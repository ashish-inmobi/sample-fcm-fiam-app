# Sample FCM FIAM app
Sample Android App to test Firebase Cloud Messaging and Firebase InApp Messaging

### There are 2 branches in this repo
* **main** with app package name ___sample.fcm___
* **glance** with app package name ___com.miui.android.fashiongallery___

### Steps to use this repo
+ Create a Firebase project and add the above 2 app package names
+ Download and add __google-services.json__ to ___app___ directory
+ Build and run the app on Android device or emulator
+ Click on ___LOG IDENTIFIERS___ button on the app screen
+ Search ___adb logcat___ for __sfMain__ to view _FCM Token & Installation ID_
