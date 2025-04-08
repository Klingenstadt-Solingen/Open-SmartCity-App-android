<div style="display:flex;gap:1%;margin-bottom:20px">
  <h1 style="border:none">Open SmartCity Core Module of the Open SmartCity App</h1>
  <img height="100px" alt="logo" src="logo.svg">
</div>

## Important Notice

- **Read-Only Repository:** This GitHub repository is a mirror of our project's source code. It is not intended for direct changes.
- **Contribution Process:** Once our Open Code platform is live, any modifications, improvements, or contributions must be made through our [Open Code](https://gitlab.opencode.de/) platform. Direct changes via GitHub are not accepted.

---

- [Important Notice](#important-notice)
- [Changelog 📝](#changelog-)
- [License](#license)

## Getting started

### Env file

Copy .env.template and rename it to .env

Change the Variables:
PARSE_URL: Url of the Parse Server backend
PARSE_CLIENT_KEY: Key used to authenticate the Parse Client
PARSE_APP_ID: Application ID registered in Parse Server

## Stadt Solingen for Android

To clone this repository with all submodules use this command

```bash
git clone -b develop https://git-dev.solingen.de/smartcityapp/oscasolingen-android.git --recurse-submodule
```

### Current Module-Lib-Versions:

version_core = 0.9 RC<br>
version_contact = 1.3.0<br>
version_coworking = 1.3.0<br>
version_defect = 1.3.0<br>
version_essentials = 1.3.0<br>
version_events = 1.3.0<br>
version_map = 1.3.0<br>
version_networkservice = 1.3.0<br>
version_pressrelease = 1.3.0<br>
version_publictransport = 1.3.0<br>
version_waste = 1.3.0<br>
version_weather = 1.3.0<br>
version_jobs = 1.3.0<br>
version_mobilitymonitor = 1.3.0<br>
version_sgart = 1.3.0<br>
version_corona = 1.3.0<br>

### Test APK

Here you can get the latest successfully generated .apk:
[newest APK](https://git-dev.solingen.de/smartcityapp/oscasolingen-android/-/raw/develop/apk/SolingenApp_0.9-RC.apk?inline=false)

## Changelog 📝

Please see the [Changelog](CHANGELOG.md).

## License

OSCA Server is licensed under the [Open SmartCity License](LICENSE.md).
