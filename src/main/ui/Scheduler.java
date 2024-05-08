package ui;

import model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import persistence.JsonReader;
import persistence.JsonWriter;

// Scheduler application
public class Scheduler {
    private static final String JSON_STORE = "./data/eventList.json";
    private Event event;
    private boolean runProgram;
    private Scanner input;
    private EventList eventList;
    private Friend friend;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private static final String EVENT_COMMAND = "event";
    private static final String CREATE_COMMAND = "create";
    private static final String ADD_FRIEND_COMMAND = "add friend";
    private static final String CHECK_FRIEND_COMMAND = "friends";
    private static final String BACK_COMMAND = "back";
    private static final String UPDATE_COMMAND = "update";
    private static final String DATE_COMMAND = "date";
    private static final String ADD_DATE_COMMAND = "add date";
    private static final String ADD_TIME_COMMAND = "add time";
    private static final String QUIT_COMMAND = "quit";
    private static final String VIEW_COMMAND = "view";
    private static final String SAVE_COMMAND = "save";
    private static final String LOAD_COMMAND = "load";

    // EFFECTS: runs the scheduler application
    public Scheduler() {
        runScheduler();
    }

    // EFFECTS: runs the scheduler application
    public void runScheduler() {
        eventList = new EventList("Events");
        runProgram = true;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        String cmd = null;
        input = new Scanner(System.in);
        startProgram();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void startProgram() {
        System.out.println("Hello!");
        printInstructions();
        String cmd;

        while (runProgram) {
            if (input.hasNext()) {
                cmd = input.nextLine();
                parseInput(cmd);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input/commands
    @SuppressWarnings("methodlength")
    private void parseInput(String cmd) {
        if (cmd.length() > 0) {
            switch (cmd) {
                case CREATE_COMMAND:
                    handleCreate();
                    break;
                case EVENT_COMMAND:
                    handleEventInfo();
                    break;
                case ADD_FRIEND_COMMAND:
                    handleAddFriend();
                    break;
                case CHECK_FRIEND_COMMAND:
                    handleFriend();
                    break;
                case BACK_COMMAND:
                    printInstructions();
                    break;
                case UPDATE_COMMAND:
                    printInstructionsUpdateEvent();
                    break;
                case ADD_DATE_COMMAND:
                    handleAddDate();
                    break;
                case DATE_COMMAND:
                    handleDate();
                    break;
                case ADD_TIME_COMMAND:
                    handleAddTime();
                    break;
                case VIEW_COMMAND:
                    printInstructionsView();
                    break;
                case QUIT_COMMAND:
                    endProgram();
                    break;
                case SAVE_COMMAND:
                    saveEventList();
                    break;
                case LOAD_COMMAND:
                    loadEventList();
                    break;
                default:
                    System.out.println("Sorry, command invalid. Please try again.");
                    break;
            }
        }
    }

    // EFFECTS: displays input/command options to user
    private void printInstructions() {
        System.out.println("\nEnter '" + CREATE_COMMAND + "' to create a new Event.");
        System.out.println("Enter '" + EVENT_COMMAND + "' to see created Events. ");
        System.out.println("Enter '" + LOAD_COMMAND + "' to load Events. ");
        System.out.println("Enter '" + SAVE_COMMAND + "' to save Events. ");
        System.out.println("Enter '" + QUIT_COMMAND + "' to quit scheduler. ");
    }

    // EFFECTS: displays input/command options to user
    private void printInstructionsV1() {
        System.out.println("\nTo update event info enter '" + UPDATE_COMMAND + "'");
        System.out.println("To view event info enter '" + VIEW_COMMAND + "'");
        System.out.println("Enter '" + CREATE_COMMAND + "' to create a new Event.");
        System.out.println("Enter '" + SAVE_COMMAND + "' to save Events. ");
        System.out.println("Enter '" + QUIT_COMMAND + "' to quit scheduler. ");
    }

    // EFFECTS: displays input/command options to user
    private void printInstructions2() {
        System.out.println("\nTo update event info enter '" + UPDATE_COMMAND + "'");
        System.out.println("To go back, enter '" + BACK_COMMAND + "'");
        System.out.println("Enter '" + SAVE_COMMAND + "' to save Events. ");
        System.out.println("Enter '" + QUIT_COMMAND + "' to quit scheduler. ");
    }

    private void printInstructionsUpdateEvent() {
        System.out.println("\nEnter '" + ADD_FRIEND_COMMAND + "' to add a friend to an event!");
        System.out.println("Enter '" + ADD_TIME_COMMAND + "' to add time slot to a friend");
        System.out.println("Enter '" + ADD_DATE_COMMAND + "' to add dates to event");
        System.out.println("To go back, enter '" + BACK_COMMAND + "'");
        System.out.println("Enter '" + SAVE_COMMAND + "' to save Events. ");
        System.out.println("Enter '" + QUIT_COMMAND + "' to quit scheduler. ");
    }

    // EFFECTS: displays input/command options to user
    private void printInstructionsV2() {
        System.out.println("\nEnter '" + CREATE_COMMAND + "' to create a new Event.");
        System.out.println("Enter '" + SAVE_COMMAND + "' to save Events. ");
        System.out.println("Enter '" + QUIT_COMMAND + "' to quit scheduler. ");
    }

    // EFFECTS: displays input/command options to user
    private void printInstructionsView() {
        System.out.println("\nEnter '" + CHECK_FRIEND_COMMAND + "' to see friends invited to event!");
        System.out.println("Enter '" + DATE_COMMAND + "' to see event dates");
        System.out.println("To go back, enter '" + BACK_COMMAND + "'");
        System.out.println("Enter '" + SAVE_COMMAND + "' to save Events. ");
        System.out.println("Enter '" + QUIT_COMMAND + "' to quit scheduler. ");

    }

    // MODIFIES: this
    // EFFECTS: creates event with inputted name
    private void handleCreate() {
        System.out.println("Please enter event name");
        String name = input.nextLine();
        event = new Event(name);
        eventList.addEvent(event);
        System.out.println("Created " + event.getEventName());
        printInstructions2();
    }



    // EFFECTS: if events have been created, prints out created event names
    private void handleEventInfo() {
        if (eventList.getSize() == 0) {
            System.out.println("There are no created events!");
            printInstructionsV2();
        } else {
            System.out.println("Scheduler has the following events:");
            printEvents();
            printInstructionsV1();
        }
    }


    // MODIFIES: this
    // EFFECTS:  creates friend with inputted name and is assigned to an event
    private void handleAddFriend() {
        if (eventList.getSize() == 0) {
            System.out.println("There are no events to add to!");
        } else {
            System.out.println("Select Event to add to:");
            System.out.println(eventList.getEventsNames());
        }
        String eventName = input.nextLine();

        if (eventList.eventExists(eventList, eventName)) {
            System.out.println("Add friend name");
            String friendName = input.nextLine();
            friend = new Friend(friendName);
            System.out.println("Adding " + friend.getName() + " to " + eventName);
            eventList.getEvent(eventName).addFriend(friend);
            printInstructionsUpdateEvent();
        } else {
            System.out.println("Sorry, command invalid. Please try again.");
            handleAddFriend();
        }
    }


    // EFFECTS: if friends have been created, prints out friend names for specific event
    private void handleFriend() {
        if (eventList.getSize() == 0) {
            System.out.println("There are no events to look at!");
        } else {
            System.out.println("Select Event to look to:");
            System.out.println(eventList.getEventsNames());
        }

        String eventName = input.nextLine();

        if (eventList.getEvent(eventName).getFriendNames().isEmpty()) {
            System.out.println("No friends :c");
        } else {
            System.out.println(eventList.getEvent(eventName).getFriendNames());
        }
        printInstructionsView();
    }

    // MODIFIES: this
    // EFFECTS: creates date that is assigned to an event
    private void handleAddDate() {
        if (eventList.getSize() == 0) {
            System.out.println("There are no events to add to!");
        } else {
            System.out.println("Select Event to look to:");
            System.out.println(eventList.getEventsNames());
        }

        String eventName = input.nextLine();

        if (eventList.eventExists(eventList, eventName)) {
            System.out.println("Day of Week:");
            String weekDay = input.nextLine();
            System.out.println("Month");
            String month = input.nextLine();
            System.out.println("Day(#)");
            int day = input.nextInt();
            System.out.println("Date created:");
            Date dateCreated = new Date(weekDay, month, day);
            System.out.println(dateCreated.getDate());
            eventList.getEvent(eventName).addDate(dateCreated);
            printInstructionsUpdateEvent();
        } else {
            System.out.println("Sorry, command invalid. Please try again.");
            handleAddDate();
        }
    }



    // EFFECTS: prints out dates that are assigned to specific date
    private void handleDate() {
        if (eventList.getSize() == 0) {
            System.out.println("There are no events to look at!");
        } else {
            System.out.println("Select Event to look to:");
            System.out.println(eventList.getEventsNames());
        }

        String eventName = input.nextLine();

        if (eventList.getEvent(eventName).getEventDates().isEmpty()) {
            System.out.println("No dates");
        } else {
            System.out.println(eventList.getEvent(eventName).getEventDates());
        }
        printInstructionsView();
    }

    // MODIFIES: this
    // EFFECTS: creates time slot for certain person/friend
    @SuppressWarnings("methodlength")
    private void handleAddTime() {
        if (eventList.getSize() == 0) {
            System.out.println("There are no events to add to!");
        } else {
            System.out.println("Select Event to look to:");
            System.out.println(eventList.getEventsNames());
        }

        String eventName = input.nextLine();

        if (eventList.eventExists(eventList, eventName)) {
            if (eventList.getEvent(eventName).getFriendNames().isEmpty()) {
                System.out.println("There are no friends to add to!");
            } else {
                System.out.println("Select Person to add time");
                System.out.println(eventList.getEvent(eventName).getFriendNames());

                String friendName = input.nextLine();

                if (event.personExists(eventList.getEvent(eventName), friendName)) {
                    System.out.println("Set start time (in format 24hr time ####)");
                    String startTime = input.nextLine();

                    System.out.println("Set end time (in format 24hr time ####)");
                    String endTime = input.nextLine();

                    Category category = readCategory();

                    System.out.println("Time Slot created:");
                    Status statusCreated = new Status(startTime, endTime, category);
                    System.out.println(statusCreated.getStatus());
                    eventList.getEvent(eventName).getFriend(friendName).addStatus(statusCreated);
                    printInstructionsUpdateEvent();
                } else {
                    System.out.println("Sorry, command invalid. Please try again.");
                    handleAddTime();
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: ends program
    public void endProgram() {
        System.out.println("Ending!");
        input.close();
    }

    // EFFECTS: prompts user to select category and returns it
    private Category readCategory() {
        System.out.println("Please select a category for your thingy");

        int menuLabel = 1;
        for (Category c : Category.values()) {
            System.out.println(menuLabel + ": " + c);
            menuLabel++;
        }

        int menuSelection = input.nextInt();
        return Category.values()[menuSelection - 1];
    }

    // EFFECTS: saves the EventList to file
    private void saveEventList() {
        try {
            jsonWriter.open();
            jsonWriter.write(eventList);
            jsonWriter.close();
            System.out.println("Saved " + eventList.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads EventList from file
    private void loadEventList() {
        try {
            eventList = jsonReader.read();
            System.out.println("Loaded " + eventList.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: prints all the events in eventList to the console
    private void printEvents() {
        List<Event> events = eventList.getEventList();

        for (Event e : events) {
            System.out.println(e.printEvent());
        }
    }
}





