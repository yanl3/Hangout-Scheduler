#  Personal Project (Phase 0)

The project that I intend to design is a scheduler which can be used to help
plan friend meetups/hangouts for larger friend groups. The application will allow 
the user to create a new event plan for a certain date or dates. Then, within the
selected dates, friends (with their name) can be "created" and be able to select a date and 
their availability information. There will be three types of times that can be added:
Not Available, May be able to clear, Available. The "created" friends and their time information
will be saved to corresponding dates. If needed, after the event plan has been created,
new dates can also be added and old dates can also be removed. The application will also 
save any progress/event plans created. 


## Summary 

*What will the application do?*

- The application will allow the user to create a new event plan where you can add and remove
certain calendar dates. The user can then add to each specific date the friend name and their 
availability time(s) distinguished in 3 categories. Each date will then display the time information
fo each friend all together. 

*Who will use it?*

- large friend groups 
- any group of people that need to organize a meeting

*Why is this project of interest to you?*

- I have a large friend group with 8 core people without the addition of a plus one. Since everyone has
different jobs, vacations, school, it is hard to find a date that works for everyone. In addition,
I wanted a system where I can input time in a 00:00 to 00:00 format instead of select and 
drag/highlight a time period format. In addition, I also want a feature to add times when you are not 
available (and signal that the rest of the time is available), such as when a person has a work shift.


## User Stories 

- As a user, I want to be able to create a new event 
- As a user, I want to be able to have multiple events at one time.
- As a user, I want to be able to add certain dates to event
- As a user, I want to be able to create a new person/name
- As a user, I want to be able to select and input when each person is available
- As a user, I want to be able to select a date and see who is available that day
- As a user, I want to be able to set what type of availability
- As a user, I want to be able to save my list of events and their info to file (if I so choose)
- As a user, I want to be able to be able to load my list of events from file (if I so choose)

## Instructions for Grader 

- You can generate the first required action related to adding Xs to a Y by creating a new event through 
the "Create New Event" button
- You can generate the second required action related to adding Xs to a Y by removing an event through the 
"Remove Event" button (but an event has to be generated and selected first)
- you can locate my visual component by the picture of my bunny as a splashscreen (only picture I had on hand)
- You can save the state of my application by pressing the "Save Events" button
- You can reload the state of my application by pressing the "Load Events" button

## Phase 4: Task 2

Wed Aug 09 17:06:35 PDT 2023
Event:event1 created

Wed Aug 09 17:06:38 PDT 2023
Event:event2 created

Wed Aug 09 17:06:40 PDT 2023
Event:event2 removed

Wed Aug 09 17:06:47 PDT 2023
Event:event3 created

Wed Aug 09 17:06:52 PDT 2023
Friend:friend1 added to Event:event1

Wed Aug 09 17:06:55 PDT 2023
Friend:friend2 added to Event:event3

Wed Aug 09 17:07:02 PDT 2023
Date:(Monday, July 4) added to Event:event1

Wed Aug 09 17:07:07 PDT 2023
Friend:friend added to Event:event3


Process finished with exit code 0


## Phase 4: Task 3 
I think i can refactor my SchedulerGUI class. I have a long method to add all my buttons to the JFrame - a 
bunch of add() used - 
which I think can be changed by creating another class that could create a button panel. In addition, 
Intellij tells me I can convert many newActionListeners() into a lambda expression, which I may want to do since
it could help simplify my code for assigning an an action once a button is pressed (less @Override). 

## References
- TellerApp
- FitLifeGymKiosk
- JSON Serialization Demo 
