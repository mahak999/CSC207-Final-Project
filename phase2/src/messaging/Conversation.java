package messaging;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Conversation object. Contains messages and metadata such as conversation name, users who have read and write access, etc.
 */
public class Conversation implements Serializable {
    private String conversationName; // either group chat or 2 person chat
    private final List<Message> conversationMessages;
    private final Set<UUID> writeAccessUsers;
    private final Set<UUID> readAccessUsers;
    private final UUID conversationUUID;
    private final Set<UUID> usersHaveRead = new HashSet<>();
    private final Set<UUID> userArchivedUUIDs = new HashSet<>();

    /**
     * Constructor for Conversation
     *
     * @param name            name of the Conversation
     * @param usersWriteUUIDs The set of users that have write access to the chat/conversation
     * @param usersReadUUIDs  The set of users that have read access to the chat/conversation
     * @param messages        A list of all the messages in the Chat/conversation
     */
    public Conversation(String name, Set<UUID> usersWriteUUIDs, Set<UUID>
            usersReadUUIDs, List<Message> messages) {
        conversationName = name;
        conversationMessages = messages;
        writeAccessUsers = usersWriteUUIDs;
        readAccessUsers = usersReadUUIDs;
        conversationUUID = UUID.randomUUID();
    }

    /**
     * returns the UUID of this conversation
     *
     * @return the UUID associated with this conversation
     */
    public UUID getConversationUUID() {
        return conversationUUID;
    }

    /**
     * Adds the User Id of the Person (user) to the list of Users that have write access
     *
     * @param userUUID UserId of the User
     */
    void grantWriteAccess(UUID userUUID) {
        writeAccessUsers.add(userUUID);
    }

    /**
     * Adds the User Id of the Person (user) to the list of Users that have read access
     *
     * @param userUUID UserId of the User
     */
    void grantReadAccess(UUID userUUID) {
        readAccessUsers.add(userUUID);
    }

    /**
     * Remove the User Id of the Person (user) to the list of Users that have write access
     *
     * @param userUUID UserId of the User
     */
    public void removeUserFromWrite(UUID userUUID) {
        writeAccessUsers.remove(userUUID);
    }

    /**
     * Remove the User Id of the Person (user) to the list of Users that have read access
     *
     * @param userUUID UserId of the User
     */
    public void removeUserFromRead(UUID userUUID) {
        readAccessUsers.remove(userUUID);
    }

    /**
     * Returns a set of UUID's of users that have write access in this conversation
     *
     * @return Set of UUID's of users that have write access for this conversation
     */
    public Set<UUID> getWriteAccessUsers() {
        return writeAccessUsers;
    }

    /**
     * Returns a set of UUID's of users that have read access in this conversation
     *
     * @return Set of UUID's of users that have read access for this conversation
     */
    public Set<UUID> getReadAccessUsers() {
        return readAccessUsers;
    }

    /**
     * Returns a list of messages in this conversation
     *
     * @return list of messages in this conversation
     */
    public List<Message> getConversationMessages() {
        return conversationMessages;
    }

    /**
     * Changes the name of the Chat (Group or private) to the new name provided
     *
     * @param newName UserId of the User
     */
    public void changeConversationName(String newName) {
        conversationName = newName;
    }


    /**
     * Gets the name of the Conversation
     */
    public String getConversationName() {
        return conversationName;
    }


    /**
     * Adds message to the list of messages in this Conversation
     *
     * @param message Message to be added in the conversation
     */
    public void addMessage(Message message) {
        if (conversationMessages.contains(message)) {
            System.out.println("Message has already been added");
        } else {
            conversationMessages.add(message);
        }
    }

    public void deleteMessage(int index) {
        conversationMessages.remove(index);
    }

    /**
     * Marks a conversation for a specific user
     *
     * @param userUUID the user having read the conversation
     */
    public void readConversation(UUID userUUID) {
        usersHaveRead.add(userUUID);
    }

    public void unreadConversation(UUID userUUID) {
        usersHaveRead.remove(userUUID);
    }

    /**
     * archives a conversation for a specific user
     *
     * @param userUUID the user archiving the conversation
     */
    public void archiveConversation(UUID userUUID) {
        userArchivedUUIDs.add(userUUID);
    }
}