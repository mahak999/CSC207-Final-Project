package gui.util.factories;

import gui.conference.general.ConferenceGeneralView;
import gui.conference.menu.ConferenceMenuView;
import gui.conference.rooms.ConferenceRoomsView;
import gui.conference.settings.ConferenceSettingsView;
import gui.conference.tabs.ConferenceTabsConstants;
import gui.conference.tabs.ConferenceTabsView;
import gui.contacts.ContactsView;
import gui.login.LoginView;
import gui.mainMenu.MainMenuView;
import gui.messaging.MessagingView;
import gui.util.enums.PanelFactoryOptions;
import gui.util.exception.NullPanelException;
import gui.util.interfaces.IFrame;
import gui.util.interfaces.IPanel;
import gui.util.interfaces.IPanelFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Creates IPanels for an IFrame given its name and some initializing arguments
 */
public class PanelFactory implements IPanelFactory {
    private IFrame mainFrame;

    /**
     * @param mainFrame main IFrame for the system
     */
    public PanelFactory(IFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    /**
     * Generate a panel given its name with no parameters
     *
     * @param name name of the panel to create
     * @return
     */
    @Override
    public IPanel createPanel(PanelFactoryOptions.panelNames name) {
        return createPanel(name, new HashMap<>());
    }

    /**
     * Generates an IPanel given its name and (optional) initializing arguments
     *
     * @param name                    name of the panel to create
     * @param initializationArguments hashmap of values that can be used to set the initial state of a panel
     * @return
     */
    @Override
    public IPanel createPanel(PanelFactoryOptions.panelNames name, Map<String, Object> initializationArguments) {
        switch (name) {
            case LOGIN:
                return new LoginView(mainFrame);
            case MAIN_MENU:
                return new MainMenuView(mainFrame, (Integer) initializationArguments.getOrDefault("defaultTabIndex", 0), initializationArguments);
            case CONFERENCE_MENU:
                return new ConferenceMenuView(mainFrame, (UUID) initializationArguments.get("defaultConferenceUUID"), initializationArguments);
            case CONFERENCE_TABS:
                return new ConferenceTabsView(mainFrame, (UUID) initializationArguments.get("conferenceUUID"), (ConferenceTabsConstants.tabNames) initializationArguments.get("defaultTabName"));
            case CONFERENCE_GENERAL:
                return new ConferenceGeneralView(mainFrame, (UUID) initializationArguments.get("conferenceUUID"));
            case CONFERENCE_SETTINGS:
                return new ConferenceSettingsView(mainFrame, (UUID) initializationArguments.get("conferenceUUID"));
            case CONFERENCE_ROOMS:
                return new ConferenceRoomsView(mainFrame, (UUID) initializationArguments.get("conferenceUUID"));
            case CONTACTS:
                return new ContactsView(mainFrame);
            case MESSAGING:
                return new MessagingView(mainFrame, (UUID) initializationArguments.get("defaultConversationUUID"), initializationArguments);
            default:
                throw new NullPanelException(name);
        }
    }
}
