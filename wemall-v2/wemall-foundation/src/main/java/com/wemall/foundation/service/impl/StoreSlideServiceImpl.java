package com.wemall.foundation.service.impl;

import com.wemall.core.dao.IGenericDAO;
import com.wemall.core.query.GenericPageList;
import com.wemall.core.query.PageObject;
import com.wemall.core.query.support.IPageList;
import com.wemall.core.query.support.IQueryObject;
import com.wemall.foundation.domain.StoreSlide;
import com.wemall.foundation.service.IStoreSlideService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StoreSlideServiceImpl
    implements IStoreSlideService {
    @Resource(name = "storeSlideDAO")
    private IGenericDAO<StoreSlide> storeSlideDao;

    public boolean save(StoreSlide storeSlide){
        try {
            this.storeSlideDao.save(storeSlide);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public StoreSlide getObjById(Long id){
        StoreSlide storeSlide = (StoreSlide)this.storeSlideDao.get(id);
        if (storeSlide != null){
            return storeSlide;
        }

        return null;
    }

    public boolean delete(Long id){
        try {
            this.storeSlideDao.remove(id);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean batchDelete(List<Serializable> storeSlideIds){
        for (Serializable id : storeSlideIds){
            delete((Long)id);
        }

        return true;
    }

    public IPageList list(IQueryObject properties){
        if (properties == null){
            return null;
        }
        String query = properties.getQuery();
        Map params = properties.getParameters();
        GenericPageList pList = new GenericPageList(StoreSlide.class, query,
                params, this.storeSlideDao);
        if (properties != null){
            PageObject pageObj = properties.getPageObj();
            if (pageObj != null)
                pList.doList(pageObj.getCurrentPage() == null ? 0 : pageObj
                             .getCurrentPage().intValue(), pageObj.getPageSize() == null ? 0 :
                             pageObj.getPageSize().intValue());
        }else{
            pList.doList(0, -1);
        }

        return pList;
    }

    public boolean update(StoreSlide storeSlide){
        try {
            this.storeSlideDao.update(storeSlide);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public List<StoreSlide> query(String query, Map params, int begin, int max){
        return this.storeSlideDao.query(query, params, begin, max);
    }
}




