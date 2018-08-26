package cn.org.caicp.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author liman <liman1@catr.cn>
 * @version 1.0.0
 * @param <T>
 * @param <ID>
 * @since 2014-4-30 11:20:50
 */
@NoRepositoryBean
public interface BaseDao<T extends Object, ID extends Serializable> extends PagingAndSortingRepository<T, ID>, JpaSpecificationExecutor<T> {

}
