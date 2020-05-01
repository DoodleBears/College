---
slideOptions:
  transition: fade
  theme: white
lang: zh,en
---

# Java SOLID Design Principles
:::info
本文主要用于记录学习到的Java SOLID 相关的知识
:::



###### tags: `编程` `Java`
<style>
.hui_Color{
   color:rgba(215, 58, 38, .95)!important;
}
</style>
## <span class="hui_Color">英文词汇</span>

- acronym —— 首字母缩写
- in this article, I will be **covering** these principles —— conclude
- violated —— to go against or refuse to obey a law, an agreement
- correct sth.
- compliant with —— meet approval
- refer to —— 指的是
    - this refers to the single responsibility principle.
- utilizing —— make use of
- segregate —— separate sb. or sth. from the group
- scope goes up —— 
    - when the application **scope goes up** or application faces certain design issues
- vice-versa —— 反之亦然


## <span class="hui_Color">笔记</span>
> write code that easy to maintain, understand, extend.

无论是从`github`上clone下来的项目，还是`IDEA（Intelli J / Eclipse）`里面的范例工程，文件的层级结构对于以前没写过项目的我来讲是相当复杂的。

但说到`maintain`管理，文件的`directory`关系想必是非常重要的。

同时，在学习了 `Java` 一个学期之后，其中大量使用到的import-library， `class` 的 `inheritance` 将不同file相关联，make it easier to `extend`

> 或许，function/method 之于单个文件，就如单个 `file` 之于整个project

The reason why we use SOLID.
![](https://i.imgur.com/4uPxhD1.png)

> not so well-written code can lead to very difficult situations

Design Pattern & Design Principle 模板&规范的主要用途应该就是make it easy to `understand` and `maintain` 在同样的规范下编写的代码，在teamwork中，不同人编写时易于理解彼此的想法，即便是经多人之手，代码的格式风格还是类似的。

when the application scope goes up or application faces **certain design issues either in production or maintenance**.

在大部分的软件项目中，除去代码的管理，功能上的更新作为整个开发loop中的主体，`追加新功能`是客户最主要的需求。为了以`OOP Principle`去编写程序，提高开发效率。势必会用到大量的 `polymorphism` `inheritance` 功能与功能之间的关系，如果不细致的管理，随着项目的增大，修改&更新便会变得更加复杂。

> 据说在整个 `software` 的“一生”中，使用OO开发的，发布和后期维护更新的比例是：3/7。即后期维护是开销的大头。

### <span class="hui_Color">S — Single responsibility principle 【各司其职】</span>

> every module or class should have responsibility over a single part of the functionality provided by the software.

每个module和class分管(负责)整个software中的一个单独的功能？

> 个人之于公司，class&module 之于整个 project



#### Do one thing and do it well

```
class User
{
    void CreatePost(Database db, string postMessage)
    {
        try
        {
            db.Add(postMessage);
        }
        catch (Exception ex)
        {
            db.LogError("An error occured: ", ex.ToString());
            File.WriteAllText("\LocalErrors.txt", ex.ToString());
        }
    }
}
```

Method `CreatePost()` 具有的功能过多
- create a new post
```
db.Add(postMessage);
```
- log an error in the database
```
db.LogError("An error occured: ", ex.ToString());
```
- log an error in a local file
```
File.WriteAllText("\LocalErrors.txt", ex.ToString());
```

#### 将 `method` ErrorLogger 分离出来

```
class ErrorLogger
{
    void log(string error)
    {
      db.LogError("An error occured: ", error);
      File.WriteAllText("\LocalErrors.txt", error);
    }
}
```

```
class Post
{
    private ErrorLogger errorLogger = new ErrorLogger();
    
    //宣告一个instance（ErrorLogger是上面自定义的class）

    void CreatePost(Database db, string postMessage)
    {
        try
        {
            db.Add(postMessage);
        }
        catch (Exception ex)
        {
            errorLogger.log(ex.ToString())
            //调用 errorLogger的method:log
        }
    }
}
```
![](https://i.imgur.com/fAE1SFU.png)



By **abstracting the functionality** that handles the error logging, we no longer violate the single responsibility principle.

Now we have **two classes that each has one responsibility**; 
1. to create a post 
2. to log an error

### <span class="hui_Color">O — Open/closed principle</span>

> software entities (classes, modules, functions, etc.) should **be open for extensions, but closed for modification**.

- **Use encapsulation to close**:用class保护data，借由method去调用data，防止data被随意modified
- **Open for extension by inheritance**：通过inheritance，override，让程式的扩展性更强，修改或增添新的method的时候，无需多次修改（提高修改效率）

**For better OOP?**

#### 1. easier to create extended

```
void CreatePost(Database db, string postMessage)
{
    if (postMessage.StartsWith("#"))
    {
        db.AddAsTag(postMessage);
    }
    else
    {
        db.Add(postMessage);
    }
}
```
> By using inheritance
```
class Post
{
    void CreatePost(Database db, string postMessage)
    {
        db.Add(postMessage);
    }
}

class TagPost extends Post
{
    @Override 
    void CreatePost(Database db, string postMessage)
    {
        db.AddAsTag(postMessage);
    }
}
```
> The evaluation of the first character ‘#’ will now be handled elsewhere (probably at a higher level) of our software
> 
> and the cool thing is, that **if we want to change** the way a postMessage is evaluated, we can change the code there, **without affecting any** of these underlying pieces of behavior.
> 

#### 2. get rid of being modified(encapsulation)

> For example, spring framework has class DispatcherServlet. This class acts as front controller for String based web applications. To use this class, we are <span class="hui_Color">**not required to modify this class**</span>. All we need is to <span class="hui_Color">**pass initialization parameters and we can extend it’s functionality the way we want**</span>.

### <span class="hui_Color">L — Liskov substitution principle</span>

> More generally it states that **objects in a program should be replaceable with instances of their subtypes** without altering the correctness of that program.

>Let ϕ(x) be a property provable about objects x of type T.
Then ϕ(y) should be true for objects y of type S, where S is a subtype of T.

孩子可以被孩子的孩子所替换？

subtypes? subclass's instance?

![](https://i.imgur.com/tRppwBB.png)

```
class Post
{
    void CreatePost(Database db, string postMessage)
    {
        db.Add(postMessage);
    }
}

class TagPost extends Post
{
    @Override
    void CreatePost(Database db, string postMessage)
    {
        db.AddAsTag(postMessage);
    }
}

class MentionPost extends Post
{
    void CreateMentionPost(Database db, string postMessage)
    {
        string user = postMessage.parseUser();

        db.NotifyUser(user);
        db.OverrideExistingMention(user, postMessage);
        base.CreatePost(db, postMessage);
    }
}
```

> - Observe how the call of CreatePost() in the case of a **subtype MentionPost** won’t do what it is supposed to do; 
> - notify the user and **override existing mention**.

Since
1. the CreatePost() method is not overridden in MentionPost
2. the CreatePost() call will simply be delegated upwards in the class
3. call CreatePost() from its parent class("Post").


> Let’s correct this
```
class MentionPost extends Post
{
    @Override
    void CreatePost(Database db, string postMessage)
    {
        string user = postMessage.parseUser();

        NotifyUser(user);
        OverrideExistingMention(user, postMessage)
        base.CreatePost(db, postMessage);
    }

    private void NotifyUser(string user)
    {
        db.NotifyUser(user);
    }

    private void OverrideExistingMention(string user, string postMessage)
    {
        db.OverrideExistingMention(_user, postMessage);
    }
}
```

**base class 具有 subclass 所具有的特性，这样subclass的 instance 也可以是base class的instance（因为其具有所有base class instance的属性）**

After overriding the "CreatePost()" method, the call won't lead to base class' method. We no longer violate the
Liskov substitution principle.

### <span class="hui_Color">I — Interface segregation principle</span>

> - In programming, the interface segregation principle states that no client should be forced to depend on methods it does not use.
> - Put more simply: Do not add additional functionality to an existing interface by adding new methods.
> - Instead, create a new interface and let your class implement multiple interfaces if needed.

```
interface IPostNew
{
    void CreatePost();
    void ReadPost();
}
```
```
interface IPostRead
{
    void ReadPost();
}
```

### <span class="hui_Color">D - Dependency inversion principle</span>

> - High-level modules should not depend on low-level modules. Both should depend on abstractions.
> - Abstractions should not depend on details. Details should depend on abstractions.
> - Typically, dependency injection is used simply by ‘injecting’ any dependencies of a class through the class’ constructor as an input parameter. 

> Let’s look at an example.

```
class Post
{
    private ErrorLogger errorLogger = new ErrorLogger();

    void CreatePost(Database db, string postMessage)
    {
        try
        {
            db.Add(postMessage);
        }
        catch (Exception ex)
        {
            errorLogger.log(ex.ToString())
        }
    }
}
```

> In this case, if we wanted to use a different kind of logger, we would **have to modify the Post class**.

In order to use the **Private** errorLogger

> private ErrorLogger errorLogger = new ErrorLogger();

we have to create an instance of class Post.


---

> By using **constructor** 
```
class Post
{
    private Logger _logger;

    public Post(Logger injectedLogger)
    {
        _logger = injectedLogger;
    }

    void CreatePost(Database db, string postMessage)
    {
        try
        {
            db.Add(postMessage);
        }
        catch (Exception ex)
        {
            logger.log(ex.ToString())
        }
    }
}
```

> By using dependency injection we no longer rely on the Post class to define the specific type of logger.
 
#### Dependency inversion in Spring framework

> In spring framework, all modules are provided as separate components which can work together by simply injected dependencies in other module.


#### 关于耦合性 —— Dependency inversion

- 程序的耦合度是 你的子程序之间的相关联性,也就是说你的多个类的联系 是否太紧密，打个比方，你房子里边有窗子 ，那房子 和窗子 就有了关联。
- 耦合度 是松还是紧 就看你的 关联 是强还是弱，也就是修改的代价，比如 你窗子是扣死在墙里的 那么你修改窗子 就必须修改墙 这就比较紧密了，但是如果你窗子是按照某种规格的 可以自由拆装的 那么修改的代价就小，耦合度也就低了。