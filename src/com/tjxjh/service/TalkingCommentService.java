package com.tjxjh.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tjxjh.po.TalkingComment;
import cn.cafebabe.websupport.service.BaseService;

@Service
public class TalkingCommentService extends BaseService{
	
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean add(TalkingComment tc){
		try{
			return super.save(tc);
		}catch(Exception e){
			System.out.println(e);
			return false;
		}
	}
	
	@Transactional (propagation = Propagation.REQUIRED) 
	public void delete(TalkingComment tc){
		 dao.delete(tc);
	}
	
	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)   
	public TalkingComment findById(Integer id){
		return dao.findById(TalkingComment.class, id);
	}
	
	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)   
	public List<TalkingComment> findByHql(Object... params){
		@SuppressWarnings("unchecked")
		List<TalkingComment> list=(List<TalkingComment>) dao.executeHql("from TalkingComment t where t.talking.id=?", params);
		return list;
	}
	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)   
	public TalkingComment findOneByHql(Object... params){
		@SuppressWarnings("unchecked")
		List<TalkingComment> list=(List<TalkingComment>) dao.executeHql("from TalkingComment t where t.talking.id=? and t.user.id=?", params);
		if(list.size()==0){
			return list.get(0);
		}
		return null;
	}
	
	@Transactional (propagation = Propagation.REQUIRED) 
	public TalkingComment update(TalkingComment tc){
		return dao.merge(tc);
	}
	
}
