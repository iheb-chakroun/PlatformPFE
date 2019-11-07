# Module 3 of Pidev (Platform PFE)
This a list of all required endpoints: Request & Response examples.
## Requirement 1: 
Get the list of students with no pfe file for a year or group of years.
```bash
Method: GET /platformPfe-web/app/student/year
```
<img src="https://i.imgur.com/hYlLlrX.png" width="880" height="400">

## Requirement 2: 
Get the list of pfe files ordred by year and/or country and/or status and/or category.
```bash
Method: GET /platformPfe-web/app/pfe/filtred
```
<img src="https://i.imgur.com/YxGADVM.png" width="880" height="400">

## Requirement 3: 
Get the list of non treated pfe files for the current year, Ordered from recent to old.
```bash
Method: GET /platformPfe-web/app/pfe/NonTreated
```
<img src="https://i.imgur.com/Nch5m0I.png" width="880" height="400">

## Requirement 4: 
Approve Internship and redirect control to head of department.
```bash
Method: POST /platformPfe-web/app/pfe/approval
Accept header : supports/prefers(application/json)
```
<img src="https://i.imgur.com/m0s9usJ.png" width="880" height="250">

## Requirement 5: 
Decline Internship and send notification via a templated email with a reason.
```bash
Method: POST /platformPfe-web/app/pfe
With QueryParam.
```
<img src="https://i.imgur.com/hdRyvc8.png" width="880" height="350">

<img src="https://i.imgur.com/ht22KwP.png" width="880" height="200">

## Requirement 6: 
Approve the cancelation of an internship.
```bash
Method: POST /platformPfe-web/app/student/annulation/approval
Accept header : supports/prefers(application/json)
```
<img src="https://i.imgur.com/28BJeoM.png" width="880" height="200">

Decline the cancelation of internship and send notification via a templated email with a reason.
```bash
Method: POST /platformPfe-web/app/student/annulation/denial
Accept header : supports/prefers(application/json)
```
<img src="https://i.imgur.com/i9u7pXt.png" width="880" height="350">

## Requirement 7: 
Give a student access to the platform.
```bash
Method: POST /platformPfe-web/app/student/authorization
Accept header : supports/prefers(application/json)
```
<img src="https://i.imgur.com/OTgUdW2.png" width="880" height="250">

## Requirement 8: 
Fix number of actions or roles that teacher can do/play in a school's site. 
```bash
Method: PUT /platformPfe-web/app/intern-dir/maximum
Accept header : supports/prefers(application/json)
```
<img src="https://i.imgur.com/p8MjOSL.png" width="880" height="350">

## Requirement 9: 
Change the status of report submission when a student deposit it at the school's administration. 
```bash
Method: POST /platformPfe-web/app/pfe/report
Accept header : supports/prefers(application/json)
```
<img src="https://i.imgur.com/kcl4ZcB.png" width="880" height="350">

## Requirement 10: 
Get the list of pfe files that are pending for thesis planification date. Means that:
Got approved( by director on internships) and validated ( by pre-validateur)
And got reviewed (got a mark/20 ) by a supervisor and a rapporteur.
```bash
Method: POST /platformPfe-web/app/pfe/planification
Accept header : supports/prefers(application/json)
```
<img src="https://i.imgur.com/VEOSL4x.png" width="880" height="350">

## Requirement 11 (MapBox api "End point consuming"): implementation Algorithm
Get the list of geographical points of students that their pfe files got approved( by director of internships) 
and validated ( by pre-validateur) based on the entreprise locations associated with their pfe files.
```bash
Method: POST /platformPfe-web/app/intern-dir/map
Accept header : supports/prefers(application/json)
```
<img src="https://i.imgur.com/Jik1921.png" width="880" height="350">



