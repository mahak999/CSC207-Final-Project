package gui.conference.event;

import gui.util.interfaces.IFrame;
import gui.util.interfaces.IPanel;

import javax.swing.*;
import java.util.Map;
import java.util.UUID;

public class EventMenuView implements IPanel, IEventMenuView{
    private JPanel panel1;
    private JButton editEventButton;
    private JButton createEventButton;
    private JList list1;
    private JSplitPane eventSplitPane;
    private JTable table1;
    private JButton button1;

    public  EventMenuView(IFrame mainFrame, UUID defaultEventUUID, Map<String, Object> initializationArguments){

    }

    @Override
    public void setEventList(String[] eventNames) {

    }

    @Override
    public void setEventListSelection(int selectionIndex) {

    }

    @Override
    public void setEventPane(IPanel tabsPanel) {

    }

    @Override
    public JPanel getPanel() {
        return null;
    }
}
