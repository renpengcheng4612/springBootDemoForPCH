package com.pc.sz.mapper;

import com.pc.sz.my.mapper.MyMapper;
import com.pc.sz.pojo.ItemsComments;
import com.pc.sz.pojo.vo.MyCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsCommentsMapperCustom extends MyMapper<ItemsComments> {

    public void saveComments(Map<String, Object> map);

    public List<MyCommentVO> queryMyComments(@Param("paramsMap") Map<String, Object> map);

}