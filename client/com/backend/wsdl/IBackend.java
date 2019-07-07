/**
 * IBackend.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.backend.wsdl;

public interface IBackend extends java.rmi.Remote {
    public com.backend.wsdl.HtmlObject getHtml(long arg0) throws java.rmi.RemoteException;
    public void updateUserCardIds(long arg0, com.backend.wsdl.LongToIntEntry[] arg1) throws java.rmi.RemoteException;
    public void setUserFoundLocationsCount(long arg0, long arg1) throws java.rmi.RemoteException;
    public com.backend.wsdl.Marker addOrUpdateMarker(com.backend.wsdl.Marker arg0) throws java.rmi.RemoteException;
    public void deleteQuest(long arg0) throws java.rmi.RemoteException;
    public void deleteMarker(long arg0) throws java.rmi.RemoteException;
    public void setTreePartFinished(long arg0, boolean arg1) throws java.rmi.RemoteException;
    public com.backend.wsdl.Enemy getEnemy(long arg0) throws java.rmi.RemoteException;
    public com.backend.wsdl.Action[] getAllActionsOfCardType(com.backend.wsdl.CardType arg0) throws java.rmi.RemoteException;
    public void setTreePartExecutedAt(com.backend.wsdl.TreePartLazy arg0) throws java.rmi.RemoteException;
    public void addDeckToUser(long arg0, long arg1) throws java.rmi.RemoteException;
    public void deleteQuestInstance(long arg0) throws java.rmi.RemoteException;
    public com.backend.wsdl.TreePartLazy addTreePartLazy(com.backend.wsdl.TreePartLazy arg0) throws java.rmi.RemoteException;
    public com.backend.wsdl.Enemy addEnemy(com.backend.wsdl.Enemy arg0) throws java.rmi.RemoteException;
    public com.backend.wsdl.Quest[] getQuestsInRange(com.backend.wsdl.MapPosition arg0, float arg1, long[] arg2) throws java.rmi.RemoteException;
    public com.backend.wsdl.User addUser(com.backend.wsdl.User arg0) throws java.rmi.RemoteException;
    public void setUserMoney(long arg0, long arg1) throws java.rmi.RemoteException;
    public void setUserTutorialPlayed(long arg0, boolean arg1) throws java.rmi.RemoteException;
    public com.backend.wsdl.Marker[] getMarkers(long[] arg0) throws java.rmi.RemoteException;
    public void addSolvedQuestToUser(long arg0, long arg1) throws java.rmi.RemoteException;
    public com.backend.wsdl.Card[] getCards(long[] arg0) throws java.rmi.RemoteException;
    public com.backend.wsdl.Marker getMarker(long arg0) throws java.rmi.RemoteException;
    public com.backend.wsdl.Quest updateQuest(com.backend.wsdl.Quest arg0) throws java.rmi.RemoteException;
    public com.backend.wsdl.EditorQuest[] getEditorQuests(long[] arg0) throws java.rmi.RemoteException;
    public void deleteFile(java.lang.String arg0) throws java.rmi.RemoteException;
    public void addUserIdToCompletedListOf(long arg0, long arg1) throws java.rmi.RemoteException;
    public com.backend.wsdl.CardImage getCardImage(long arg0) throws java.rmi.RemoteException;
    public com.backend.wsdl.EditorQuest updateEditorQuest(com.backend.wsdl.EditorQuest arg0) throws java.rmi.RemoteException;
    public com.backend.wsdl.Card[] getBooster(long arg0) throws java.rmi.RemoteException;
    public com.backend.wsdl.Action[] getAllActions() throws java.rmi.RemoteException;
    public com.backend.wsdl.Card getCard(long arg0) throws java.rmi.RemoteException;
    public com.backend.wsdl.RandomEnemy getRandomEnemy(long arg0) throws java.rmi.RemoteException;
    public void addCardToUser(long arg0, long arg1) throws java.rmi.RemoteException;
    public com.backend.wsdl.QuestRating addQuestRating(com.backend.wsdl.QuestRating arg0) throws java.rmi.RemoteException;
    public void setQuestInstanceHighlighted(long arg0, boolean arg1) throws java.rmi.RemoteException;
    public void removeCreatedQuestFromUser(long arg0, long arg1) throws java.rmi.RemoteException;
    public void setUserWonFightsCount(long arg0, long arg1) throws java.rmi.RemoteException;
    public void setUserStartedFightsCount(long arg0, long arg1) throws java.rmi.RemoteException;
    public void deleteAction(long arg0) throws java.rmi.RemoteException;
    public void deleteDeck(long arg0) throws java.rmi.RemoteException;
    public com.backend.wsdl.Card addCard(com.backend.wsdl.Card arg0) throws java.rmi.RemoteException;
    public com.backend.wsdl.RandomEnemy[] getRandomEnemiesInRange(com.backend.wsdl.MapPosition arg0, float arg1, long[] arg2) throws java.rmi.RemoteException;
    public com.backend.wsdl.EditorQuest getEditorQuest(long arg0) throws java.rmi.RemoteException;
    public void setTreePartActive(long arg0, boolean arg1) throws java.rmi.RemoteException;
    public void setQuestInstanceFightSkipped(long arg0, boolean arg1) throws java.rmi.RemoteException;
    public void addCreatedCardToUser(long arg0, long arg1) throws java.rmi.RemoteException;
    public com.backend.wsdl.Quest getQuest(long arg0) throws java.rmi.RemoteException;
    public void setTreePartOpened(long arg0, boolean arg1) throws java.rmi.RemoteException;
    public com.backend.wsdl.Card updateCard(com.backend.wsdl.Card arg0) throws java.rmi.RemoteException;
    public java.lang.String downloadFile(java.lang.String arg0) throws java.rmi.RemoteException;
    public void setTreePartHighlightedInvisibleMarker(long arg0, boolean arg1) throws java.rmi.RemoteException;
    public void addCreatedQuestToUser(long arg0, long arg1) throws java.rmi.RemoteException;
    public void setUserAnsweredQuestionsCount(long arg0, long arg1) throws java.rmi.RemoteException;
    public void deleteUser(long arg0) throws java.rmi.RemoteException;
    public void removeDeckFromUser(long arg0, long arg1) throws java.rmi.RemoteException;
    public void setUserTaskCount(long arg0, long arg1) throws java.rmi.RemoteException;
    public void setQuestInstanceFailCount(long arg0, long arg1) throws java.rmi.RemoteException;
    public com.backend.wsdl.EditorQuest addEditorQuest(com.backend.wsdl.EditorQuest arg0) throws java.rmi.RemoteException;
    public void deleteCardImage(long arg0) throws java.rmi.RemoteException;
    public void setUserKmWalked(long arg0, double arg1) throws java.rmi.RemoteException;
    public com.backend.wsdl.Action updateAction(com.backend.wsdl.Action arg0) throws java.rmi.RemoteException;
    public com.backend.wsdl.QuestInstance[] getQuestInstances(long[] arg0) throws java.rmi.RemoteException;
    public void addQuestInstanceToUser(long arg0, long arg1) throws java.rmi.RemoteException;
    public com.backend.wsdl.QuestInstance addQuestInstance(com.backend.wsdl.QuestInstance arg0) throws java.rmi.RemoteException;
    public void createRandomEnemies() throws java.rmi.RemoteException;
    public com.backend.wsdl.Deck getDeck(long arg0) throws java.rmi.RemoteException;
    public void removeCreatedCardFromUser(long arg0, long arg1) throws java.rmi.RemoteException;
    public java.lang.String uploadFile(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public com.backend.wsdl.CardImage updateCardImage(com.backend.wsdl.CardImage arg0) throws java.rmi.RemoteException;
    public com.backend.wsdl.Deck addDeck(com.backend.wsdl.Deck arg0) throws java.rmi.RemoteException;
    public com.backend.wsdl.HtmlObject addHtml(com.backend.wsdl.HtmlObject arg0) throws java.rmi.RemoteException;
    public com.backend.wsdl.User updateUser(com.backend.wsdl.User arg0) throws java.rmi.RemoteException;
    public com.backend.wsdl.Action getAction(long arg0) throws java.rmi.RemoteException;
    public com.backend.wsdl.QuestInstance getQuestInstance(long arg0) throws java.rmi.RemoteException;
    public com.backend.wsdl.CardImage addCardImage(com.backend.wsdl.CardImage arg0) throws java.rmi.RemoteException;
    public void removeQuestInstanceFromUser(long arg0, long arg1) throws java.rmi.RemoteException;
    public com.backend.wsdl.Deck[] getDecks(long[] arg0) throws java.rmi.RemoteException;
    public void setQuestInstanceOfTreePart(long arg0, long arg1) throws java.rmi.RemoteException;
    public void updateUserDeckIds(long arg0, long[] arg1) throws java.rmi.RemoteException;
    public com.backend.wsdl.TreePart addOrUpdateTreePart(com.backend.wsdl.TreePart arg0) throws java.rmi.RemoteException;
    public void deleteCard(long arg0) throws java.rmi.RemoteException;
    public void register(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public com.backend.wsdl.QuestInstance updateQuestInstance(com.backend.wsdl.QuestInstance arg0) throws java.rmi.RemoteException;
    public void deleteTreePart(long arg0) throws java.rmi.RemoteException;
    public com.backend.wsdl.HtmlObject updateHtml(com.backend.wsdl.HtmlObject arg0) throws java.rmi.RemoteException;
    public com.backend.wsdl.Deck updateDeck(com.backend.wsdl.Deck arg0) throws java.rmi.RemoteException;
    public com.backend.wsdl.Action[] getActions(long[] arg0) throws java.rmi.RemoteException;
    public com.backend.wsdl.User login(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public com.backend.wsdl.Quest[] getQuests(long[] arg0) throws java.rmi.RemoteException;
    public com.backend.wsdl.Action addAction(com.backend.wsdl.Action arg0) throws java.rmi.RemoteException;
    public com.backend.wsdl.Quest addQuest(com.backend.wsdl.Quest arg0) throws java.rmi.RemoteException;
    public com.backend.wsdl.TreePart getTreePart(long arg0) throws java.rmi.RemoteException;
    public void setQuestInstanceVisibleOnMap(long arg0, boolean arg1) throws java.rmi.RemoteException;
    public com.backend.wsdl.IntegrationConfigParams convertPicture(com.backend.wsdl.IntegrationConfigParams arg0) throws java.rmi.RemoteException;
    public com.backend.wsdl.Deck getStandartDeck(long arg0) throws java.rmi.RemoteException;
    public void setRotateMapForUser(long arg0, boolean arg1) throws java.rmi.RemoteException;
    public com.backend.wsdl.Enemy updateEnemy(com.backend.wsdl.Enemy arg0) throws java.rmi.RemoteException;
}
