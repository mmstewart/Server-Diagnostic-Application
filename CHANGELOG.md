# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).

**All updates are before version 1 release!**

## [Update 1] - 2018-07-18
### Fixed
- Keyboard pop up error

## [Update 2] - 2018-07-19
### Fixed
- Inconsistent buttons and UI of Server Diagnostic Report

## [Update 3] - 2018-07-20
### Changed
- Updated UI in ServerReportScreen class  
### Added
- Added a progress bar when URL is loading  
- Buttons disabled when not in use  
- ScrollView for TextView Results

## [Update 4] - 2018-07-23
### Changed
- Updated UI in ServerReportScree class  
### Added
- Users can now use Username, Password, and Tokem fields
## [Update 5] - 2018-07-25
### Removed
- Buttons disabled when not in use
### Added
- Buttons are present on launch
- Submit button informs user to enter URL when missing
- Error symbol  to textResult lines when userr has invalid URL or missing URL
### Fixed
- Invalid or missing URLs no longer crash app

## [Update 6] - 2018-07-26
### Removed
- Down arrow on the Spinner in activity_server_report_screen.kt
- 'Server Diagnostic Report' text in activity_server_report_screen.kt
### Changed
- UI layout of activity_server_report_screen.kt
### Added
- Character count of 30 for Username EditText in activity_server_report_screen.kt
- Password visibility option for Password EditText in activity_server_report_screen.kt

## [Update 7] - 2018-08-02
### Security
- Requirements so that the application crashes less
### Added
- Travis ci and Codecov shields

[0.3.0]: https://github.com/robinst/autolink-java/compare/autolink-0.2.0...autolink-0.3.0
[0.2.0]: https://github.com/robinst/autolink-java/compare/autolink-0.1.0...autolink-0.2.0
