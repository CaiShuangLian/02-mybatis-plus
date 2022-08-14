# Mybatis-Plus demo

简单的会议室预约功能



实现功能：预约，批量添加会议室，批量修改会议室信息，批量取消预约，联合模糊查询会议室，分页

## 表设计
**会议室表（room）**

```SQL
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `room_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会议室名称',
  `start_time` time(0) NULL DEFAULT NULL COMMENT '开始预约的时间',
  `end_time` time(0) NULL DEFAULT NULL COMMENT '结束预约时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
```

| id             | int(11)**        |
| :------------- | ---------------- |
| **room_name**  | **varchar(255)** |
其中start_time——end_time表示可以预约的时间段，精确到时:分



**订单表（order）**

```SQL 
ROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
   `room_id` int(11) NULL DEFAULT NULL COMMENT '会议室id',
  `status` int(3) NULL DEFAULT NULL COMMENT '状态：0取消预约，1拒绝申请，2进行中，3已完成，4审核中',
  `price` double(10, 2) NULL DEFAULT NULL COMMENT '实际支付价格',
  `start_time` time(0) NULL DEFAULT NULL COMMENT '预定开始时间',
  `end_time` time(0) NULL DEFAULT NULL COMMENT '预定结束时间',
  `appoint_day` date NULL DEFAULT NULL COMMENT '预定日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
```

| id              | int(11)           |
| --------------- | ----------------- |
| **phone**       | **varchar(255)**  |
| **room_id**     | **int(11)**       |
| **status**      | **int(3)**        |
| **price**       | **double(10, 2)** |
| **start_time**  | **time(0)**       |
| **end_time**    | **time(0)**       |
| **appoint_day** | **date**          |

其中start_time——end_time表示预约的时间段，精确到 时:分

appoint_day 表示预约的日期，精确到 年:月:日





## 接口设计实现

### 预约

根据所选会议室的id，预约日期和预约开始和结束时间，预约人，发起请求

```json
{
  "appointDay": "2022-08-13",
  "endTime": "20:00",
  "id": 1,
  "phone": 18090202385,
  "startTime": "19:00"
}
```

时间上不冲突并且在room的可预约时间内才可以预约成功，下面是做一个简单的时间线是否重叠的判断

```xml
<select id="check" resultType="java.lang.Boolean">
    SELECT
        COUNT(1)
    FROM
        `order` LEFT JION `room` ON `order`.room_id=`room`.id
    WHERE
        `order`.room_id = #{id}
        AND `order`.`status`=2
        AND `order`.appoint_day = #{appointDay}
        AND (( `order`.end_time &gt;= #{startTime} 
    			AND `order`.end_time &lt;= #{endTime} )
            OR ( `order`.start_time &gt;= #{startTime} 
    			AND `order`.start_time &lt;= #{endTime} )
            OR ( `order`.start_time &lt;= #{startTime} 
    			AND `order`.end_time &gt;= #{endTime} ))
        AND `room`.start_time &lt;= #{startTime}
        AND `room`.end_time &gt;= #{endTime}
</select>
```

数据库中返回记录数为0才可预约



### 批量添加

批量添加会议室并返回添加的数据

```json
[
  {
    "endTime": "22:00",
    "id": 0,
    "price": 1,
    "roomName": "会议室test01",
    "startTime": "9:00"
  },
    {
    "endTime": "22:00",
    "id": 0,
    "price": 1,
    "roomName": "会议室test02",
    "startTime": "9:00"
  }
]
```

响应成功的返回值

```json
{
  "status": 200,
  "msg": "SUCCESS",
  "data": [
    {
      "id": 54,
      "roomName": "会议室test01",
      "price": 1,
      "startTime": "9:00",
      "endTime": "22:00"
    },
    {
      "id": 55,
      "roomName": "会议室test02",
      "price": 1,
      "startTime": "9:00",
      "endTime": "22:00"
    }
  ]
}
```

主要实现如下：

```xml
<insert id="batchInsert" useGeneratedKeys="true" keyProperty="id">
        INSERT INTO room (room_name, price, start_time, end_time) VALUES
        <foreach item="roomVos" collection="list" separator=",">
            (#{roomVos.roomName}, 
            #{roomVos.price},
            #{roomVos.startTime}, 
            #{roomVos.endTime})
        </foreach>
  </insert>
```

设置`useGeneratedKeys`为`true`，表示当主键自增时，返回插入成功时的主键id，返回的值对应`keyProperty`设置的返回属性

实现批量是通过` <foreach >`标签

其中：

- **collection:** 需做foreach(遍历)的对象，作为入参时，list、array对象时，collection属性值分别默认用"list"、"array"代替，Map对象没有默认的属性值。但是，在作为入参时可以使用@Param(“keyName”)注解来设置自定义collection属性值，设置keyName后，list、array会失效；
- **item：** 集合元素迭代时的别名称，该参数为必选项；
- **index：** 在list、array中，index为元素的序号索引。但是在Map中，index为遍历元素的key值，该参数为可选项；
- **open：** 遍历集合时的开始符号，通常与close=")"搭配使用。使用场景IN(),values()时，该参数为可选项；
- **separator：** 元素之间的分隔符，类比在IN()的时候，separator=",",最终所有遍历的元素将会以设定的（,）逗号符号隔开，该参数为可选项；
- **close：** 遍历集合时的结束符号，通常与open="("搭配使用，该参数为可选项；

### 批量修改

对应的批量修改

```xml
<update id="batchUpdate">
        UPDATE room
        <set>
            <trim prefix="room.room_name=case" suffix="end,">
                <foreach collection="list" item="roomVos" index="index">
                    <if test="roomVos.roomName !=null">
                        when room.id=#{roomVos.id} then #{roomVos.roomName}
                    </if>
                </foreach>
            </trim>

            <trim prefix="room.price=case" suffix="end,">
                <foreach collection="list" item="roomVos" index="index">
                    <if test="roomVos.price !=null">
                        when room.id=#{roomVos.id} then #{roomVos.price}
                    </if>
                </foreach>
            </trim>

            <trim prefix="room.start_time=case" suffix="end,">
                <foreach collection="list" item="roomVos" index="index">
                    <if test="roomVos.startTime !=null">
                        when room.id=#{roomVos.id} then #{roomVos.startTime}
                    </if>
                </foreach>
            </trim>

            <trim prefix="room.end_time=case" suffix="end,">
                <foreach collection="list" item="roomVos" index="index">
                    <if test="roomVos.endTime !=null">
                        when room.id=#{roomVos.id} then #{roomVos.endTime}
                    </if>
                </foreach>
            </trim>
        </set>

        where room.id in
        <foreach collection="list" item="roomVos" index="index" separator="," open="(" close=")">
            #{roomVos.id}
        </foreach>
    </update>
```

对应的SQL语句：

```SQL
UPDATE room 
SET 
room.room_name = CASE	
		WHEN room.id =? THEN ? 
		WHEN room.id =? THEN ? 
		WHEN room.id =? THEN ? 
	END,
room.price = CASE
		WHEN room.id =? THEN ? 
		WHEN room.id =? THEN ? 
		WHEN room.id =? THEN ? 
	END,
room.start_time = CASE
		WHEN room.id =? THEN ? 
		WHEN room.id =? THEN ? 
		WHEN room.id =? THEN ? 
	END,
room.end_time = CASE	
		WHEN room.id =? THEN ? 
		WHEN room.id =? THEN ? 
		WHEN room.id =? THEN ? 
	END 
WHERE room.id IN (?,?,? )
```



通过case when语句作批量修改，比将foreach设置在整条语句外面，逐条update的速度快



`<set>`标签

可以去除最后一个多余的逗号



`<if>`标签，通过判断`<if>`标签内的条件是否成立，决定最后的SQL是否拼接，类似于java中的`if`语句，通常配合`<set>`标签使用

类似的还有`<choose>`标签，搭配`<when>`和`<otherwise>`标签使用





`<trim>`标签

拼接SQL，可以在`<trim>`包裹的部分（内容有效时）设置前缀和后缀，`prefix`前缀，`suffix`后缀

还可以删除前缀和后缀的某字符，`prefixOverrides/suffixOverrides`



批量取消预约

```xml
<update id="cancel">
        UPDATE `order`
        <set>
            `order`.status=0
        </set>
        where `order`.phone=#{phone}
        AND (`order`.`status`= 2 or `order`.`status`=4)
        AND `order`.id in
        <foreach collection="ids" item="ids" index="ids" 
                 separator="," open="(" close=")">
            #{ids}
        </foreach>
</update>
```



### 批量删除

```xml
<delete id="batchDelete">
        DELETE FROM room
        where room.id in
        <foreach collection="list" item="ids" index="index" separator="," open="(" close=")">
            #{ids}
        </foreach>
  </delete>
```

通过`<foreach>`标签即可实现



### 联合模糊查询



```xml
<select id="getList" resultMap="RoomDTO">
        SELECT
            room.id,
            room.room_name,
            room.price,
            CONCAT(
            DATE_FORMAT( room.start_time, '%H:%i' ),
            '-',
            DATE_FORMAT( room.end_time, '%H:%i' )) AS roomTime,
            GROUP_CONCAT(
            DATE_FORMAT( o.start_time, '%H:%i' ),
            '-',
            DATE_FORMAT( o.end_time, '%H:%i' )) AS appointTime
        FROM
            room
        LEFT JOIN 
    		(SELECT * FROM `order` WHERE `order`.appoint_day = #{appointDay} ) 
    		o ON room.id = o.room_id
        <where>
            <if test="keyword!=null and keyword!=''">
                and
                (room_name like concat ('%',#{keyword},'%')
                or room_address like concat ('%',#{keyword},'%')
                or room_description like concat ('%',#{keyword},'%')
                or company_name like concat ('%',#{keyword},'%'))
            </if>
            <if test="price!=null">
                and room.price &lt;= #{price}
            </if>
        </where>
        GROUP BY
            room.id
        ORDER BY
            o.start_time
    </select>
```



`<where>`标签

可以去除where后跟的多余的and，所以需要将and写在语句前面

不使用该标签，可以通过1=1避免多余的and导致的SQL异常



`<bind>`标签

创建一个变量并绑定到上下文中，比如`like`语句，既可以使用concat函数，如上所示，也可以写成

~~~sql
 <bind name="pattern" value="'%' + _parameter.getKeyword + '%'" />
  SELECT * FROM BLOG
  ```
  room_name like #{pattern}
  ```
~~~

需要注意的是，参数名必须是_parameter



### 分页

通过PageHelper实现分页

```java
public RespBean getList(RoomVo roomVo) {
        //注意是调用SQL之前使用,否则不生效
        PageHelper.startPage(roomVo.getPageNum(),roomVo.getPageSize());

        List<RoomDTO> roomDTOList=roomMapper.getList(roomVo);
        PageInfo<RoomDTO> pageInfo=new PageInfo<RoomDTO>(roomDTOList);

        return RespBean.success(pageInfo);
    }
```

前端请求数据（联合模糊查询返回的列表）

```json
{
  "appointDay": "2022-08-13",
  "keyword": "1",
  "pageNum": 1,
  "pageSize": 2,
  "price": 100000
}
```

`pageNum`开始的页数，`pageSize`每页的条数



响应数据

```json
{
  "status": 200,
  "msg": "SUCCESS",
  "data": {
    "total": 4, //数据总数
    "list": [
      {
        "id": 4,
        "roomName": "会议室test",
        "price": 1,
        "roomTime": "09:00-22:00",
        "appointTime": null
      },
      {
        "id": 6,
        "roomName": "会议室1-4",
        "price": 300,
        "roomTime": "09:00-00:00",
        "appointTime": null
      }
    ],
    "pageNum": 1, 
    "pageSize": 2,
    "size": 2, 
    "startRow": 1, 
    "endRow": 2,
    "pages": 2, 
    "prePage": 0,
    "nextPage": 2,
    "isFirstPage": true,
    "isLastPage": false,
    "hasPreviousPage": false,
    "hasNextPage": true,
    "navigatePages": 8,
    "navigatepageNums": [
      1,
      2
    ],
    "navigateFirstPage": 1,
    "navigateLastPage": 2
  }
}
```



## generator

代码生成器(generator包内)

```Java
public class CodeGenerator {
    /**
     *    * <p>
     *    * 读取控制台内容
     *    * </p>
     *    
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
        String ipt = scanner.next();
        if (StringUtils.isNotBlank(ipt)) {
            return ipt;
        }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
  }
  

    public static void main(String[] args) throws ParseException {
      // 代码生成器
      AutoGenerator mpg = new AutoGenerator();
      // 全局配置
      GlobalConfig gc = new GlobalConfig();
      String projectPath = System.getProperty("user.dir");
      gc.setOutputDir(projectPath + "/src/main/java");
      //作者
      gc.setAuthor("CaiShuangLian");
      //打开输出目录
      gc.setOpen(false);
      //xml开启 BaseResultMap
      gc.setBaseResultMap(true);
      //xml 开启BaseColumnList
      gc.setBaseColumnList(true);
      //日期格式，采用Date
      gc.setDateType(DateType.ONLY_DATE);
      mpg.setGlobalConfig(gc);
      // 数据源配置
      DataSourceConfig dsc = new DataSourceConfig();
      
      dsc.setUrl("jdbc:mysql://localhost:3306/room_booking?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia"+"/Shanghai");
      dsc.setDriverName("com.mysql.cj.jdbc.Driver");
      dsc.setUsername("root");
      dsc.setPassword("1234");
      mpg.setDataSource(dsc);
      // 包配置
      PackageConfig pc = new PackageConfig();
      pc.setParent("com.mp.demo")
           .setEntity("data.entity")
           .setMapper("mapper")
           .setService("service")
           .setServiceImpl("service.impl")
           .setController("controller");
      mpg.setPackageInfo(pc);
      // 自定义配置
      InjectionConfig cfg = new InjectionConfig() {
        @Override
            public void initMap() {
            // to do nothing
            Map<String, Object> map = new HashMap<>();
            map.put("date1", "1.0.0");
            this.setMap(map);
        }
    
        };
      // 如果模板引擎是 freemarker
      String templatePath = "/templates/mapper.xml.ftl";
      // 如果模板引擎是 velocity
      // String templatePath = "/templates/mapper.xml.vm";
      // 自定义输出配置
      List<FileOutConfig> focList = new ArrayList<>();
      // 自定义配置会被优先输出
      focList.add(new FileOutConfig(templatePath) {
        @Override
            public String outputFile(TableInfo tableInfo) {
            // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
            return projectPath + "/src/main/resources/mapper/" +
                        tableInfo.getEntityName() + "Mapper"
                  +StringPool.DOT_XML;
        }
    
        });
      cfg.setFileOutConfigList(focList);
      mpg.setCfg(cfg);
      // 配置模板
      TemplateConfig templateConfig = new TemplateConfig()
           .setEntity("templates/entity2.java")
           .setMapper("templates/mapper2.java")
           .setService("templates/service2.java")
           .setServiceImpl("templates/serviceImpl2.java")
           .setController("templates/controller2.java");
      templateConfig.setXml(null);
      mpg.setTemplate(templateConfig);
        // 策略配置
      StrategyConfig strategy = new StrategyConfig();
      //数据库表映射到实体的命名策略
      strategy.setNaming(NamingStrategy.underline_to_camel);
      //数据库表字段映射到实体的命名策略
      strategy.setColumnNaming(NamingStrategy.underline_to_camel);
      //lombok模型
      strategy.setEntityLombokModel(true);
      //生成 @RestController 控制器
      // strategy.setRestControllerStyle(true);
      strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
      strategy.setControllerMappingHyphenStyle(true);
      //表前缀
//      strategy.setTablePrefix("t_");
      mpg.setStrategy(strategy);
      mpg.setTemplateEngine(new FreemarkerTemplateEngine());
      mpg.execute();
  }
}
```

