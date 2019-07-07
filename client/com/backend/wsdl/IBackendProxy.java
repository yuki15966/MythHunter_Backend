package com.backend.wsdl;

public class IBackendProxy implements com.backend.wsdl.IBackend {
  private String _endpoint = null;
  private com.backend.wsdl.IBackend iBackend = null;
  
  public IBackendProxy() {
    _initIBackendProxy();
  }
  
  public IBackendProxy(String endpoint) {
    _endpoint = endpoint;
    _initIBackendProxy();
  }
  
  private void _initIBackendProxy() {
    try {
      iBackend = (new com.backend.wsdl.BackendServiceLocator()).getBackendPort();
      if (iBackend != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iBackend)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iBackend)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iBackend != null)
      ((javax.xml.rpc.Stub)iBackend)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.backend.wsdl.IBackend getIBackend() {
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend;
  }
  
  public com.backend.wsdl.HtmlObject getHtml(long arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.getHtml(arg0);
  }
  
  public void updateUserCardIds(long arg0, com.backend.wsdl.LongToIntEntry[] arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.updateUserCardIds(arg0, arg1);
  }
  
  public void setUserFoundLocationsCount(long arg0, long arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.setUserFoundLocationsCount(arg0, arg1);
  }
  
  public com.backend.wsdl.Marker addOrUpdateMarker(com.backend.wsdl.Marker arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.addOrUpdateMarker(arg0);
  }
  
  public void deleteQuest(long arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.deleteQuest(arg0);
  }
  
  public void deleteMarker(long arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.deleteMarker(arg0);
  }
  
  public void setTreePartFinished(long arg0, boolean arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.setTreePartFinished(arg0, arg1);
  }
  
  public com.backend.wsdl.Enemy getEnemy(long arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.getEnemy(arg0);
  }
  
  public com.backend.wsdl.Action[] getAllActionsOfCardType(com.backend.wsdl.CardType arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.getAllActionsOfCardType(arg0);
  }
  
  public void setTreePartExecutedAt(com.backend.wsdl.TreePartLazy arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.setTreePartExecutedAt(arg0);
  }
  
  public void addDeckToUser(long arg0, long arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.addDeckToUser(arg0, arg1);
  }
  
  public void deleteQuestInstance(long arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.deleteQuestInstance(arg0);
  }
  
  public com.backend.wsdl.TreePartLazy addTreePartLazy(com.backend.wsdl.TreePartLazy arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.addTreePartLazy(arg0);
  }
  
  public com.backend.wsdl.Enemy addEnemy(com.backend.wsdl.Enemy arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.addEnemy(arg0);
  }
  
  public com.backend.wsdl.Quest[] getQuestsInRange(com.backend.wsdl.MapPosition arg0, float arg1, long[] arg2) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.getQuestsInRange(arg0, arg1, arg2);
  }
  
  public com.backend.wsdl.User addUser(com.backend.wsdl.User arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.addUser(arg0);
  }
  
  public void setUserMoney(long arg0, long arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.setUserMoney(arg0, arg1);
  }
  
  public void setUserTutorialPlayed(long arg0, boolean arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.setUserTutorialPlayed(arg0, arg1);
  }
  
  public com.backend.wsdl.Marker[] getMarkers(long[] arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.getMarkers(arg0);
  }
  
  public void addSolvedQuestToUser(long arg0, long arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.addSolvedQuestToUser(arg0, arg1);
  }
  
  public com.backend.wsdl.Card[] getCards(long[] arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.getCards(arg0);
  }
  
  public com.backend.wsdl.Marker getMarker(long arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.getMarker(arg0);
  }
  
  public com.backend.wsdl.Quest updateQuest(com.backend.wsdl.Quest arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.updateQuest(arg0);
  }
  
  public com.backend.wsdl.EditorQuest[] getEditorQuests(long[] arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.getEditorQuests(arg0);
  }
  
  public void deleteFile(java.lang.String arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.deleteFile(arg0);
  }
  
  public void addUserIdToCompletedListOf(long arg0, long arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.addUserIdToCompletedListOf(arg0, arg1);
  }
  
  public com.backend.wsdl.CardImage getCardImage(long arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.getCardImage(arg0);
  }
  
  public com.backend.wsdl.EditorQuest updateEditorQuest(com.backend.wsdl.EditorQuest arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.updateEditorQuest(arg0);
  }
  
  public com.backend.wsdl.Card[] getBooster(long arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.getBooster(arg0);
  }
  
  public com.backend.wsdl.Action[] getAllActions() throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.getAllActions();
  }
  
  public com.backend.wsdl.Card getCard(long arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.getCard(arg0);
  }
  
  public com.backend.wsdl.RandomEnemy getRandomEnemy(long arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.getRandomEnemy(arg0);
  }
  
  public void addCardToUser(long arg0, long arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.addCardToUser(arg0, arg1);
  }
  
  public com.backend.wsdl.QuestRating addQuestRating(com.backend.wsdl.QuestRating arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.addQuestRating(arg0);
  }
  
  public void setQuestInstanceHighlighted(long arg0, boolean arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.setQuestInstanceHighlighted(arg0, arg1);
  }
  
  public void removeCreatedQuestFromUser(long arg0, long arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.removeCreatedQuestFromUser(arg0, arg1);
  }
  
  public void setUserWonFightsCount(long arg0, long arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.setUserWonFightsCount(arg0, arg1);
  }
  
  public void setUserStartedFightsCount(long arg0, long arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.setUserStartedFightsCount(arg0, arg1);
  }
  
  public void deleteAction(long arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.deleteAction(arg0);
  }
  
  public void deleteDeck(long arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.deleteDeck(arg0);
  }
  
  public com.backend.wsdl.Card addCard(com.backend.wsdl.Card arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.addCard(arg0);
  }
  
  public com.backend.wsdl.RandomEnemy[] getRandomEnemiesInRange(com.backend.wsdl.MapPosition arg0, float arg1, long[] arg2) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.getRandomEnemiesInRange(arg0, arg1, arg2);
  }
  
  public com.backend.wsdl.EditorQuest getEditorQuest(long arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.getEditorQuest(arg0);
  }
  
  public void setTreePartActive(long arg0, boolean arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.setTreePartActive(arg0, arg1);
  }
  
  public void setQuestInstanceFightSkipped(long arg0, boolean arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.setQuestInstanceFightSkipped(arg0, arg1);
  }
  
  public void addCreatedCardToUser(long arg0, long arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.addCreatedCardToUser(arg0, arg1);
  }
  
  public com.backend.wsdl.Quest getQuest(long arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.getQuest(arg0);
  }
  
  public void setTreePartOpened(long arg0, boolean arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.setTreePartOpened(arg0, arg1);
  }
  
  public com.backend.wsdl.Card updateCard(com.backend.wsdl.Card arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.updateCard(arg0);
  }
  
  public java.lang.String downloadFile(java.lang.String arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.downloadFile(arg0);
  }
  
  public void setTreePartHighlightedInvisibleMarker(long arg0, boolean arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.setTreePartHighlightedInvisibleMarker(arg0, arg1);
  }
  
  public void addCreatedQuestToUser(long arg0, long arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.addCreatedQuestToUser(arg0, arg1);
  }
  
  public void setUserAnsweredQuestionsCount(long arg0, long arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.setUserAnsweredQuestionsCount(arg0, arg1);
  }
  
  public void deleteUser(long arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.deleteUser(arg0);
  }
  
  public void removeDeckFromUser(long arg0, long arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.removeDeckFromUser(arg0, arg1);
  }
  
  public void setUserTaskCount(long arg0, long arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.setUserTaskCount(arg0, arg1);
  }
  
  public void setQuestInstanceFailCount(long arg0, long arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.setQuestInstanceFailCount(arg0, arg1);
  }
  
  public com.backend.wsdl.EditorQuest addEditorQuest(com.backend.wsdl.EditorQuest arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.addEditorQuest(arg0);
  }
  
  public void deleteCardImage(long arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.deleteCardImage(arg0);
  }
  
  public void setUserKmWalked(long arg0, double arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.setUserKmWalked(arg0, arg1);
  }
  
  public com.backend.wsdl.Action updateAction(com.backend.wsdl.Action arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.updateAction(arg0);
  }
  
  public com.backend.wsdl.QuestInstance[] getQuestInstances(long[] arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.getQuestInstances(arg0);
  }
  
  public void addQuestInstanceToUser(long arg0, long arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.addQuestInstanceToUser(arg0, arg1);
  }
  
  public com.backend.wsdl.QuestInstance addQuestInstance(com.backend.wsdl.QuestInstance arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.addQuestInstance(arg0);
  }
  
  public void createRandomEnemies() throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.createRandomEnemies();
  }
  
  public com.backend.wsdl.Deck getDeck(long arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.getDeck(arg0);
  }
  
  public void removeCreatedCardFromUser(long arg0, long arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.removeCreatedCardFromUser(arg0, arg1);
  }
  
  public java.lang.String uploadFile(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.uploadFile(arg0, arg1);
  }
  
  public com.backend.wsdl.CardImage updateCardImage(com.backend.wsdl.CardImage arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.updateCardImage(arg0);
  }
  
  public com.backend.wsdl.Deck addDeck(com.backend.wsdl.Deck arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.addDeck(arg0);
  }
  
  public com.backend.wsdl.HtmlObject addHtml(com.backend.wsdl.HtmlObject arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.addHtml(arg0);
  }
  
  public com.backend.wsdl.User updateUser(com.backend.wsdl.User arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.updateUser(arg0);
  }
  
  public com.backend.wsdl.Action getAction(long arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.getAction(arg0);
  }
  
  public com.backend.wsdl.QuestInstance getQuestInstance(long arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.getQuestInstance(arg0);
  }
  
  public com.backend.wsdl.CardImage addCardImage(com.backend.wsdl.CardImage arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.addCardImage(arg0);
  }
  
  public void removeQuestInstanceFromUser(long arg0, long arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.removeQuestInstanceFromUser(arg0, arg1);
  }
  
  public com.backend.wsdl.Deck[] getDecks(long[] arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.getDecks(arg0);
  }
  
  public void setQuestInstanceOfTreePart(long arg0, long arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.setQuestInstanceOfTreePart(arg0, arg1);
  }
  
  public void updateUserDeckIds(long arg0, long[] arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.updateUserDeckIds(arg0, arg1);
  }
  
  public com.backend.wsdl.TreePart addOrUpdateTreePart(com.backend.wsdl.TreePart arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.addOrUpdateTreePart(arg0);
  }
  
  public void deleteCard(long arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.deleteCard(arg0);
  }
  
  public void register(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.register(arg0, arg1);
  }
  
  public com.backend.wsdl.QuestInstance updateQuestInstance(com.backend.wsdl.QuestInstance arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.updateQuestInstance(arg0);
  }
  
  public void deleteTreePart(long arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.deleteTreePart(arg0);
  }
  
  public com.backend.wsdl.HtmlObject updateHtml(com.backend.wsdl.HtmlObject arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.updateHtml(arg0);
  }
  
  public com.backend.wsdl.Deck updateDeck(com.backend.wsdl.Deck arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.updateDeck(arg0);
  }
  
  public com.backend.wsdl.Action[] getActions(long[] arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.getActions(arg0);
  }
  
  public com.backend.wsdl.User login(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.login(arg0, arg1);
  }
  
  public com.backend.wsdl.Quest[] getQuests(long[] arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.getQuests(arg0);
  }
  
  public com.backend.wsdl.Action addAction(com.backend.wsdl.Action arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.addAction(arg0);
  }
  
  public com.backend.wsdl.Quest addQuest(com.backend.wsdl.Quest arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.addQuest(arg0);
  }
  
  public com.backend.wsdl.TreePart getTreePart(long arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.getTreePart(arg0);
  }
  
  public void setQuestInstanceVisibleOnMap(long arg0, boolean arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.setQuestInstanceVisibleOnMap(arg0, arg1);
  }
  
  public com.backend.wsdl.IntegrationConfigParams convertPicture(com.backend.wsdl.IntegrationConfigParams arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.convertPicture(arg0);
  }
  
  public com.backend.wsdl.Deck getStandartDeck(long arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.getStandartDeck(arg0);
  }
  
  public void setRotateMapForUser(long arg0, boolean arg1) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    iBackend.setRotateMapForUser(arg0, arg1);
  }
  
  public com.backend.wsdl.Enemy updateEnemy(com.backend.wsdl.Enemy arg0) throws java.rmi.RemoteException{
    if (iBackend == null)
      _initIBackendProxy();
    return iBackend.updateEnemy(arg0);
  }
  
  
}