# asice-back
通用管理后台

## 询价模块表结构设计

#### 询价信息  inquiry_info

- 客户信息表  inquiry_boss


 名称 | 字段 |备注 |
    ---|--- |--- 
|  客户id   |inquiry_boss_id |
|  公司名称   |inquiry_boss_compan |
|  联系人名称     |inquiry_boss 	   |
|  联系电话   |inquiry_boss_telePh |	
|  联系邮箱   |inquiry_boss_email  | 
|  QQ号       |inquiry_boss_QQ	   |   
|  微信号     |inquiry_boss_wechat |
|  客户创建时间     |inquiry_boss_createtime |
|  客户更新时间     |inquiry_boss_updatetime |
|  状态     |status | 0:正常; -1:数据已失效 |


- 客户询价记录表  inquiry_goods

名称 | 字段 |备注
    ---|--- |--- 
|   询价记录编号     |  inquiry_goods_id             |
|   询价品牌（有可能含有很长的描述信息）|  inquiry_goods_brand	        |
|   询价型号        |  inquiry_goods_model	        |  可能数据会比较多 |
|   询价数量        |  inquiry_goods_count	        |                   |
|   询价铭牌        |  inquiry_goods_brandCard_pic	|  需要图片上传，product_pic_id 外键    |
|   询价备注        |  inquiry_goods_note	        |                   |
|   询价时间        |  inquiry_goods_createtime     |                   |
|   客户id          |  inquiry_boss_id              | ``` 询价人信息表主键 ``` |
|   员工id          |  inquiry_staff_id             |    接待客户的员工id      |
|   状态            |  status                       | 0:正常;<br> -1:数据已失效    |


- 产品图片  product_pic

名称 | 字段 |备注
    ---|--- |--- 
|  产品图片主键     |  product_pic_id         |
|  询价/报价货物编号     |  inquiry_goods_id       |
|  图片访问路径     |  product_pic_url        |
|  创建时间         |  product_pic_createtime |





- 报价记录表

名称 | 字段 |备注
    ---|--- |--- 
|   报价单主键      |   offer_id           |                      |
|   询价记录表主键  |   inquiry_goods_id   | 关联客户询价记录主键 |
|   报价品牌        |   offer_brand        |（核验与询价品牌一致）|	
|   报价型号        |   offer_model        |	                   
|   报价数量        |   offer_count        |	
|   含税运单价      |   offer_price        |	
|   货期（文本备注）|   offer_shipment	   |                      |
|   报价备注        |   offer_note	       |
|   报价时间        |   offer_time	       |
|   报价人userid    |   offer_staff        |                      |
|   询价进度 | offer_step | 1:询价中(待报价);<br>2:待反馈(已报价);<br>3:客户犹豫中(待跟进);<br>4:客户放弃;<br>5:已下单 |
|   状态            |   status             |  0:正常; -1:数据已失效 |




