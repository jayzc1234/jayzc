这里统一定义数据迁移文件名称格式为：
```
Prefix + Version + Separator + Description + Suffix
来标识一个数据迁移文件。
比如: V1.0.1__Add_new_table.sql 这个例子：
前缀（Prefix） => V 

版本号（Version）=> 1.0.1

分隔符（Separator）=> __ （默认采用双下划线）

文件描述（Description）=> Add_new_table

后缀（Suffix）=> .sql
```

 flyway追踪迁移文件的生命周期是通过版本号来鉴定的，
 版本号采用最左前缀原则进行匹对
 比如：1.10.1 > 1.0.3 > 1.0.2 > 1.0.1 
 这种形式。
 
 服务的每次启动都会记录该次的最新的版本号，下次启动时会自动配对版本号是否有所更新
 ，更新则执行最新的数据迁移文件。
 
 
 由于我们是基于现有数据库进行flyway的版本控制，所以我们需要制定一个
 版本作为当前数据库的基础版本（也就是我们常说的基准线）。
 
＃在针对没有模式历史记录表的非空模式执行迁移时是否自动调用基线。 
＃然后，在执行迁移之前，将使用baselineVersion初始化此架构。 
＃仅适用于基线版本以上的迁移。 
＃这对于具有现有数据库的项目上的初始Flyway生产部署很有用。 
＃启用此功能时要小心，因为它会消除确保 
＃如果配置错误，Flyway不会迁移错误的数据库！（默认值：false）
＃flyway.baselineOnMigrate = true

