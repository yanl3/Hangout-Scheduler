package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import persistence.*;

import model.*;
import model.Event;

// main window of scheduler
public class SchedulerGUI extends JFrame {
    private static final String JSON_STORE = "./data/eventList2.json";
    private static final int WIDTH = 500;
    private static final int HEIGHT = 550;
    private EventList eventList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private DefaultListModel<String> list1;


    // construct main window
    // EFFECTS: sets up window in which program will show

    public SchedulerGUI() {
        super("EventPlanner");

        addButtons();

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                printEventLog();
                System.exit(0);
            }
        });

        setSize(WIDTH, HEIGHT);
        centreOnScreen();
        setLayout(null);
        setVisible(true);

    }

    // MODIFIES: eventList, this
    // EFFECTS: adds buttons with corresponding function and can place on window that calls method
    @SuppressWarnings("methodlength")
    private void addButtons() {
        eventList = new EventList("Events");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        list1 = new DefaultListModel<>();
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        JButton b = new JButton("Create New Event");
        b.setBounds(215, 110, 150, 30);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputString = JOptionPane.showInputDialog(null, "Enter Event Name:");
                if (inputString != null) {
                    var output = "Created Event: " + inputString;
                    JOptionPane.showMessageDialog(null, output);
                    Event event = new Event(inputString);
                    eventList.addEvent(event);
                    list1.addElement(event.getEventName());
                } else {
                    JOptionPane.showMessageDialog(null, "No event created");
                }
            }
        });


        JList<String> list = new JList<>(list1);
        list.setBounds(25, 100, 170, 300);

        JLabel labelList = new JLabel("<<Event List>>");
        labelList.setBounds(50, 60, 100, 50);


        JButton showEventDetail = new JButton("View Event");
        showEventDetail.setBounds(215, 210, 150, 30);
        showEventDetail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data = "";
                if (list.getSelectedIndex() != -1) {
                    int place = list.getSelectedIndex();
                    data = getStringInfo(eventList.getEvent(list1.elementAt(place)));
                    JOptionPane.showMessageDialog(null, data);
                }
            }
        });

        JButton removeEvent = new JButton("Remove Event");
        removeEvent.setBounds(215, 160, 150, 30);
        removeEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list.getSelectedIndex() != -1) {
                    int place = list.getSelectedIndex();
                    list1.remove(place);
                    eventList.removeEvent(place);
                    JOptionPane.showMessageDialog(null, "Removed Event.");
                }
            }
        });

        JButton addFriend = new JButton("Add Friend");
        addFriend.setBounds(215, 290, 150, 30);
        addFriend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list.getSelectedIndex() != -1) {
                    int place = list.getSelectedIndex();
                    Event event = eventList.getEvent(list1.elementAt(place));
                    String inputString = JOptionPane.showInputDialog(null, "Enter Friend Name:");
                    event.addFriend(new Friend(inputString));
                }
            }
        });


        JButton saveFile = new JButton("Save Events");
        saveFile.setBounds(35, 420, 150, 30);
        saveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(eventList);
                    jsonWriter.close();
                    JOptionPane.showMessageDialog(null, "Saved " + eventList.getName() + " to " + JSON_STORE);
                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "Unable to write to file: " + JSON_STORE);
                }
            }
        });


        JButton loadFile = new JButton("Load Events");
        loadFile.setBounds(35, 460, 150, 30);
        loadFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    eventList = jsonReader.read();
                    for (Event e : eventList.getEventList()) {
                        list1.addElement(e.getEventName());
                    }
                    JOptionPane.showMessageDialog(null, "Loaded " + eventList.getName() + " from " + JSON_STORE);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE);
                }
            }
        });


        JButton addDate = new JButton(("Add Date"));
        addDate.setBounds(215, 340, 150, 30);
        addDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list.getSelectedIndex() != -1) {
                    int place = list.getSelectedIndex();
                    Event event = eventList.getEvent(list1.elementAt(place));

                    String inputString = JOptionPane.showInputDialog(null, "Enter Day of Week");
                    String inputString2 = JOptionPane.showInputDialog(null, "Enter Month");
                    String inputString3 = JOptionPane.showInputDialog(null, "Enter Day (Number)");

                    int dayNumber = Integer.parseInt(inputString3);

                    Date date = new Date(inputString, inputString2, dayNumber);
                    event.addDate(date);
                }
            }
        });


        add(labelList);
        add(saveFile);
        add(loadFile);
        add(removeEvent);
        add(list);
        add(b);
        add(addDate);
        add(showEventDetail);
        add(addFriend);
    }


    //EFFECTS: centres the window in the middle of screen when opened
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    // EFFECTS: returns formatted/string information of event
    private String getStringInfo(Event e) {
        if (e.getFriendsList().isEmpty() && e.getEventDates().isEmpty()) {
            return "There is no event info!";
        }
        return "Date(s): " + e.getEventDates() + "\nFriends + Status: " + e.getFriendsList();
    }

    // EFFECTS: prints out eventLog to console
    private void printEventLog() {
        for (Event1 event1 : EventLog.getInstance()) {
            System.out.println(event1.toString());
        }
    }


    // runs the scheduler GUI
    public static void main(String[] args) {
        new SplashWindow();
        new SchedulerGUI();
    }

}
