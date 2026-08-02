# TorchVault - Torch Disguise Vault App

## Features
- Torch app disguise
- 3-second long press to unlock vault
- PIN setup (first time)
- PIN entry for vault access
- Glassmorphism UI design
- AES-256 encryption for security
- Vault categories: Photos, Videos, Audio, Documents, Hidden Apps

## GitHub Actions Build

### Step 1: Upload to GitHub
1. Create new repository on GitHub
2. Upload all files from this ZIP
3. Make sure `.github/workflows/build.yml` is included

### Step 2: Run Workflow
1. Go to Actions tab
2. Click "Build APK & AAB"
3. Click "Run workflow"
4. Wait 5-10 minutes
5. Download APK from Artifacts

### Step 3: Install
1. Transfer APK to phone
2. Enable "Unknown Sources" in Settings
3. Install APK

## File Structure
```
TorchVault/
├── .github/workflows/build.yml
├── app/
│   ├── build.gradle.kts
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/example/torchvault/
│       │   ├── MainActivity.kt
│       │   ├── navigation/
│       │   ├── security/
│       │   ├── ui/
│       │   └── viewmodel/
│       └── res/
├── build.gradle.kts
├── settings.gradle.kts
└── gradle.properties
```

## Tech Stack
- Kotlin
- Jetpack Compose
- Navigation Compose
- EncryptedSharedPreferences
- AES-256-GCM Encryption
