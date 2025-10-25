# CareerCheer

CareerCheer is a simple, user-friendly Android app designed to help students track their internship and job hunting progress through the application stages.

**Focus**: Tech Career Track

<img src="./Cheer.png" alt="App Logo" height="150"/>

## Tech Stack

- **UI:** Jetpack Compose, Material 3
- **Architecture:** MVVM
- **Database/DAta Storage:** Room
- **Navigation:** Jetpack Compose Navigation component
- **Language:** Kotlin (Android Studio IDE)

## Setup and Running

1. Open Android Studio (eg. Narwhal 25.1.2)
2. Clone repository
   - git clone ```https://github.com/wong-ja/CareerCheer.git```
3. Build (Gradle sync) & Run 'app' on an Android device or emulator
   - __Note__: upon first Gradle sync, may take a few mins
   - Android 8.0+ (API 26+) needed
   - eg. Android 16.0 ("Baklava") (API 36.0)
4. On each run, sample data will be inserted automatically
   - __Note__: existing data is from previous runs
6. You can add, delete and/or modify applications on this app as needed!

## Brief on Project Structure

- `/CareerCheer/app/src/main/java/com/example/careercheer/data` -- Room, objects, interface to database/data/logic (in device storage)
- `/CareerCheer/app/src/main/java/com/example/careercheer/viewmodel` -- ViewModels for interation between UI-data, flows, queries, updates to db
- `/CareerCheer/app/src/main/java/com/example/careercheer/ui`-- -- UI/app/screen components (eg. application form, full application list, etc.)
- `/CareerCheer/app/src/main/java/com/example/careercheer/ui/theme` -- adapts Material 3 themes/colors, 

## Usage & Features

- View a list of applications in various stages/progress
- Add, edit, delete job applications
  - title/position, company, location, description, job requirements, application link, tech stack, notes, application status, date
- Switch between different application statuses/interview stages (color-coded)
  - Applied, BehavioralInterview, TechnicalInterview, TakeHomeAssessment, Onsite, Whiteboard, Offer, Accepted, Rejected, PositionFilled
- Search applications by keywords/strings/text
- Filter applications by status
- Storage on physical device (Room, offline storage)

<img src='https://github.com/wong-ja/CareerCheer/blob/main/CareerCheer.gif' title='Video Demo' width='300' alt='Video Demo' />

## Future Features To Implement

- [ ] Add date selection for application stages -- better for tracking deadlines
- [ ] Improve sorting/filtering functionality -- by date, text, application status, et.
- [ ] Add notifications/reminders for upcoming application stages (eg. TechnicalInterview)
- [ ] Calender support/sync across devices (cloud)
- [ ] ...

## Relevant Links

- [Project Slides](https://docs.google.com/presentation/d/1cGz08eMRJCVz6jznCAByW_RDRnLS2P0OPy6Wf_bHAlo/edit?usp=sharing)
- [Devpost Submission](https://devpost.com/software/careercheer)
- [YouTube - Install & Run](https://youtu.be/NJxoO6fF3C4)

## Team Pixel Python!

<!-- - Sofia Ivann  -->
- Juana Wong 
<!-- - Akkeem Tyrell  -->
<!-- - Baoying Liang Wu -->
