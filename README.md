# PhonePe Secure 🛡️

[![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=flat&logo=kotlin&logoColor=white)](https://kotlinlang.org/) 
[![Firebase](https://img.shields.io/badge/Firebase-FFCA28?style=flat&logo=firebase&logoColor=black)](https://firebase.google.com/) 
[![Android](https://img.shields.io/badge/Android-3DDC84?style=flat&logo=android&logoColor=white)](https://developer.android.com/)
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)

> A smart Android security application that leverages machine learning and real-time monitoring to detect fraudulent transactions and protect users from financial fraud.

---

## 🎯 Overview

PhonePe Secure is an intelligent fraud detection system that monitors your transaction notifications in real-time, analyzes spending patterns, and alerts you to suspicious activities. Built with modern Android development practices and powered by Firebase, it provides comprehensive financial security with beautiful data visualizations.

## ✨ Key Features

### 🔍 **Smart Fraud Detection**
- Real-time transaction monitoring via NotificationListenerService
- Advanced pattern recognition for suspicious activities
- Instant alerts for potential fraudulent transactions
- Customizable detection sensitivity

### 📊 **Spend Analytics**
- Interactive daily spending charts powered by MPAndroidChart
- Historical transaction analysis
- Spending category breakdowns
- Export reports functionality

### ☁️ **Cloud Integration**
- Real-time data synchronization with Firebase Firestore
- Secure cloud storage with Firebase Storage
- Cross-device data accessibility
- Automatic backup and restore

### 🎨 **Modern UI/UX**
- Material Design 3 implementation
- Dynamic theme support (Light/Dark/System)
- Smooth animations and transitions
- Intuitive navigation and user flow

## 📱 Screenshots

<table>
  <tr>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/2847c336-680a-4633-ba5f-e95e33e03d6b" width="200px" />
      <br />
      <strong>Splash Screen</strong>
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/42bb2a83-dd2a-4805-be30-2a786b4f5dff" width="200px" />
      <br />
      <strong>Home Dashboard</strong>
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/afc2cd18-45c0-4a30-998c-18ab65cdb594" width="200px" />
      <br />
      <strong>Fraud Alerts</strong>
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/a9dc142f-db0d-4524-9806-f26ab1d3cf3e" width="200px" />
      <br />
      <strong>Analytics Charts</strong>
    </td>
  </tr>
</table>

## 🛠️ Tech Stack

| Category | Technologies |
|----------|-------------|
| **Languages** | Kotlin, Java |
| **Architecture** | MVVM, Clean Architecture |
| **Backend** | Firebase Firestore, Firebase Storage |
| **Charts** | MPAndroidChart |
| **UI** | Material Design 3, ViewBinding |
| **Security** | Android Keystore, Encryption |

## 🚀 Getting Started

### Prerequisites

- Android Studio Arctic Fox or newer
- Android SDK 21+
- Firebase project setup
- Google Services JSON file

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/AryantKumar/PhonepeSecure.git
   cd PhonepeSecure
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an existing project"
   - Navigate to the cloned directory

3. **Configure Firebase**
   - Create a new Firebase project
   - Add your Android app to the project
   - Download `google-services.json`
   - Place it in the `app/` directory

4. **Build and Run**
   ```bash
   ./gradlew build
   ```
   - Connect your Android device or start an emulator
   - Click "Run" in Android Studio

### Required Permissions

The app requires the following permissions:
- `BIND_NOTIFICATION_LISTENER_SERVICE` - For monitoring transaction notifications
- `INTERNET` - For Firebase connectivity
- `ACCESS_NETWORK_STATE` - For network status monitoring

## 🔧 Configuration

1. **Enable Notification Access**
   - Go to Settings > Apps > Special Access > Notification Access
   - Enable PhonePe Secure

2. **Firebase Setup**
   - Ensure your `google-services.json` is properly configured
   - Verify Firestore and Storage rules are set up

## 📋 Usage

1. **Initial Setup**: Grant necessary permissions when prompted
2. **Dashboard**: View your transaction summary and alerts
3. **Analytics**: Explore spending patterns with interactive charts
4. **Alerts**: Configure fraud detection sensitivity
5. **Settings**: Customize themes and notification preferences

## 🤝 Contributing

We welcome contributions! Here's how you can help:

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/AmazingFeature`)
3. **Commit** your changes (`git commit -m 'Add some AmazingFeature'`)
4. **Push** to the branch (`git push origin feature/AmazingFeature`)
5. **Open** a Pull Request

### Contribution Guidelines

- Follow Kotlin coding conventions
- Add unit tests for new features
- Update documentation as needed
- Ensure CI/CD checks pass

## 🐛 Issues & Support

Found a bug or have a feature request? Please [open an issue](https://github.com/AryantKumar/PhonepeSecure/issues) with:

- Clear description of the problem
- Steps to reproduce
- Expected vs actual behavior
- Device and Android version info

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

```
MIT License

Copyright (c) 2025 AryantKumar

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
```

## 🙏 Acknowledgments

- [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart) for beautiful chart implementations
- [Firebase](https://firebase.google.com/) for robust backend services
- [Material Design](https://material.io/) for design guidelines
- Open source community for inspiration and support

## 📞 Contact

**AryantKumar** - [@AryantKumar](https://github.com/AryantKumar)

Project Link: [https://github.com/AryantKumar/PhonepeSecure](https://github.com/AryantKumar/PhonepeSecure)

---

<div align="center">
  <strong>⭐ Star this repository if you found it helpful!</strong>
</div>
