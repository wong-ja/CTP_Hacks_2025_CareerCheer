# CareerCheer

CareerCheer is a simple, user-friendly Android app designed to help students track their internship and job hunt progress through the application stages.

**Focus**: Tech Career Track

## Tech Stack

- **UI:** Jetpack Compose, Material 3
- **Architecture:** MVVM
- **Database/DAta Storage:** Room
- **Navigation:** Jetpack Compose Navigation component
- **Language:** Kotlin (Android Studio IDE)

## Setup and Running

1. Open Android Studio
2. Clone repository
   - ```git clone https://github.com/wong-ja/CareerCheer.git```
3. Build & run on (Gradle sync) an Android device or emulator 
   - Android 8.0+ (API 26+) needed
4. On first run, sample data will be inserted automatically
5. You can add, delete and/or modify applications on this app as needed!

## Brief on Project Structure

- `/data` -- Room, objects, interface to database/data/logic (in device storage)
- `/viewmodel` -- ViewModels for interation between UI-data, flows, queries, updates to db
- `/ui`-- -- UI/app/screen components (eg. application form, full application list, etc.)
- `/ui/theme` -- adapts Material 3 themes/colors, 

## Usage & Features

- View a list of applications in various stages/progress
- Add, edit, delete job applications
  - title/position, company, location, description, job requirements, application link, tech stack, notes, application status, date
- Switch between different application statuses/interview stages (color-coded)
  - Applied, BehavioralInterview, TechnicalInterview, TakeHomeAssessment, Onsite, Whiteboard, Offer, Accepted, Rejected, PositionFilled
- Search applications by keywords/strings/text
- Filter applications by status
- Storage on physical device (Room, offline storage)

## Future Features To Implement

- [ ] Add date selection for application stages -- better for tracking deadlines
- [ ] Improve sorting/filtering functionality -- by date, text, application status, et.
- [ ] Add notifications/reminders for upcoming application stages (eg. TechnicalInterview)
- [ ] Calender support/sync across devices (cloud)
- [ ] ...

## Team Pixel Python!

- Sofia Ivann 
- Juana Wong 
- Akkeem Tyrell 
- Baoying Liang Wu
