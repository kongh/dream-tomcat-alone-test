package com.yidiantong.cms.content.services.impls;

import com.yidiantong.cms.CmsConstants;
import com.yidiantong.cms.content.daos.CategoryRepository;
import com.yidiantong.cms.content.dtos.SimpleCategory;
import com.yidiantong.cms.content.models.CategoryEntity;
import com.yidiantong.cms.content.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springside.modules.utils.mapper.BeanMapper;

import java.util.List;

/**
 * Created by konghang on 2017/8/18.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * 查找指定板块、指定类型的分类条目
     *
     * @param moduleId
     * @param type
     * @return
     */
    @Override
    public List<SimpleCategory> findEnabledSimpleByModuleIdAndType(Long moduleId, String type, Integer page, Integer size) {
        PageRequest request = new PageRequest(page, size);
        List<CategoryEntity> entities = categoryRepository.findByModuleIdAndTypeAndStatus(moduleId, type, CmsConstants.ContentStatus.ONLINED.getCode(), request);
        return BeanMapper.mapList(entities, CategoryEntity.class, SimpleCategory.class);
    }

    /**
     * 查找
     *
     * @param type
     * @param module
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<SimpleCategory> findEnabledSimpleByTypeAndModule(String type, String module, Integer page, Integer size) {
        PageRequest request = new PageRequest(page, size);
        List<CategoryEntity> entities = categoryRepository.findByTypeAndCodeStartsWithAndStatus(type, module, CmsConstants.ContentStatus.ONLINED.getCode(), request);
        return BeanMapper.mapList(entities, CategoryEntity.class, SimpleCategory.class);
    }

    /**
     * 查找
     *
     * @param type
     * @param module
     * @return
     */
    @Override
    public List<SimpleCategory> findEnabledSimpleByTypeAndModule(String type, String module) {
        List<CategoryEntity> entities = categoryRepository.findByTypeAndCodeStartsWithAndStatus(type, module, CmsConstants.ContentStatus.ONLINED.getCode());
        return BeanMapper.mapList(entities, CategoryEntity.class, SimpleCategory.class);
    }

    /**
     * 查找
     *
     * @param module
     * @return
     */
    @Override
    public List<SimpleCategory> findEnabledSimpleByModule(String module) {
        List<CategoryEntity> entities = categoryRepository.findByCodeStartsWithAndStatus(module, CmsConstants.ContentStatus.ONLINED.getCode());
        return BeanMapper.mapList(entities, CategoryEntity.class, SimpleCategory.class);
    }

    /**
     * 查找
     *
     * @param id
     * @return
     */
    @Override
    public SimpleCategory findOneSimpleById(Long id) {
        return BeanMapper.map(categoryRepository.findOne(id), SimpleCategory.class);
    }

    /**
     * 查找
     *
     * @param ids
     * @return
     */
    @Override
    public List<SimpleCategory> findSimpleByIds(List<Long> ids) {
        List<CategoryEntity> entities = categoryRepository.findAll(ids);
        return BeanMapper.mapList(entities, CategoryEntity.class, SimpleCategory.class);
    }

    /**
     * 查找
     *
     * @param type
     * @return
     */
    @Override
    public List<SimpleCategory> findSimpleByType(String type) {
        List<CategoryEntity> categoryEntities = categoryRepository.findByTypeAndStatus(type, CmsConstants.ContentStatus.ONLINED.getCode());
        return BeanMapper.mapList(categoryEntities, CategoryEntity.class, SimpleCategory.class);
    }
}
