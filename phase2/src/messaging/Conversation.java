package messaging;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Conversation object. Contains messages and metadata such as conversation name, users who have read and write access, etc.
 */
public class Conversation implements Serializable {
    private String conversationName; // either group chat or 2 person chat
    private List<Message> conversationMessages;
    private Set<UUID> writeAccessUsers;
    private Set<UUID> readAccessUsers;
    private UUID convoId;

    /**
     * Constructor for Conversation
     *
     * @param convName     name of the Conversation
     * @param usersWrite   The set of users that have write access to the chat/conversation
     * @param usersRead    The set of users that have read access to the chat/conversation
     * @param convMessages A list of all the messages in the Chat/conversation
     */
    public Conversation(String convName, Set<UUID> usersWrite, Set<UUID>
            usersRead, List<Message> convMessages) {
        conversationName = convName;
        conversationMessages = convMessages;
        writeAccessUsers = usersWrite;
        readAccessUsers = usersRead;
        convoId = UUID.randomUUID();
    }

    /**
     * returns the UUID of this conversation
     *
     * @return the UUID associated with this conversation
     */
    public UUID getconvId() {
        return convoId;
    }

    /**
     * Adds the User Id of the Person (user) to the list of Users that have write access
     *
     * @param userUUID UserId of the User
     */
    public void addUserToWrite(UUID userUUID) {
        writeAccessUsers.add(userUUID);
    }

    /**
     * Adds the User Id of the Person (user) to the list of Users that have read access
     *
     * @param userUUID UserId of the User
     */
    public void addUserToRead(UUID userUUID) {
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
     * Gets the name of the Conversation
     *
     * @param conversationId The UUID associated with this Conversation
     */
    public String getConversationName(UUID conversationId) {
        return conversationName;
    }

    /**
     * Adds message to the list of messages in this Conversation
     *
     * @param message Message to be added in the conversation
     * @return true iff message was sent successfully
     */
    public boolean addMessage(Message message) {
        if (conversationMessages.contains(message)) {
            System.out.println("Message has already been added");
            return false;
        } else {
            conversationMessages.add(message);
            return true;
        }
    }

    /**
     * Deletes a specific message in the Chat
     *
     * @param message Message to be deleted
     * @return true iff message was deleted successfully
     */
    public boolean deleteMessage(Message message) {
        if (conversationMessages.contains(message)) {
            conversationMessages.remove(message);
            return true;
        } else {
            return false;
        }
    }

    /**
     * archives a specific message for a specific user
     *
     * @param message message in question
     * @param userUUid user in question
     * @return whether the message has been archived or not
     */
    public boolean archiveMessage (Message message, UUID userUUid) {
        if (conversationMessages.contains(message)) {
            message.setUsersArchivingMessage(userUUid);
            return true;
        } else {
            return false;
        }

    }

    /**
     * marks a specific message as 'read' for a specific user
     *
     * @param message message in question
     * @param userUUID user in question
     * @return true if the message is marked as read, false otherwise
     */
    public boolean readMessage(Message message, UUID userUUID){
        if (conversationMessages.contains(message)) {
            message.userReadMessage(userUUID);
            return true;
        } else {
            return false;
        }
    }

    /**
     * unmarks a specific message as 'read' for a specific user
     *
     * @param message message in question
     * @param userUUID user in question
     * @return true if the message is unmarked as read, false otherwise
     */
    public boolean unreadMessage(Message message, UUID userUUID){
        if (conversationMessages.contains(message)) {
            message.userUnreadMessage(userUUID);
            return true;
        } else {
            return false;
        }
    }
}