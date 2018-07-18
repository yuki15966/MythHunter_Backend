ALTER TABLE treepart 
ADD opened boolean;
UPDATE treepart set opened = false;

delete FROM player_activequestids;
delete from treepart_successors where predecessorid in (select treepart.id from treepart where treepart.id in (select questinstance.treeinstancerootid from questinstance) or treepart.active = true or treepart.finished = true) ;
delete from treepart where id in (select questinstance.treeinstancerootid from questinstance) or active = true or finished = true;
delete From questinstance;
delete from player_solvedquestids;