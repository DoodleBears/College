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


## <span class="hui_Color">笔记</span>
> write code that easy to maintain, understand, extend.

The reason why we use SOLID.

### <span class="hui_Color">S — Single responsibility principle</span>
> every module or class should have responsibility over a single part of the functionality provided by the software.

每个module和class分管(负责)整个software中的一个单独的功能？

> “Do one thing and do it well

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

Method CreatePost() has too much responsibility, given that it can both create a new post, log an error in the database, and log an error in a local file.


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

By abstracting the functionality that handles the error logging, we no longer violate the single responsibility principle.

Now we have **two classes that each has one responsibility**; 
1. to create a post 
2. to log an error

### <span class="hui_Color">O — Open/closed principle</span>

> software entities (classes, modules, functions, etc.) should **be open for extensions, but closed for modification**.

For better OOP?

#### easier to create extended

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

class TagPost : Post
{
    override void CreatePost(Database db, string postMessage)
    {
        db.AddAsTag(postMessage);
    }
}
```
> The evaluation of the first character ‘#’ will now be handled elsewhere (probably at a higher level) of our software, and the cool thing is, that if we want to change the way a postMessage is evaluated, we can change the code there, without affecting any of these underlying pieces of behavior.

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

class TagPost : Post
{
    override void CreatePost(Database db, string postMessage)
    {
        db.AddAsTag(postMessage);
    }
}

class MentionPost : Post
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

> - Observe how the call of CreatePost() in the case of a subtype MentionPost won’t do what it is supposed to do; 
> - notify the user and **override existing mention**.

Since
1. the CreatePost() method is not overridden in MentionPost
2. the CreatePost() call will simply be delegated upwards in the class
3. call CreatePost() from its parent class("Post").


> Let’s correct this
```
class MentionPost : Post
{
    override void CreatePost(Database db, string postMessage)
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

#### 关于耦合性 —— Dependency inversion

- 程序的耦合度是 你的子程序之间的相关联性,也就是说你的多个类的联系 是否太紧密，打个比方，你房子里边有窗子 ，那房子 和窗子 就有了关联。
- 耦合度 是松还是紧 就看你的 关联 是强还是弱，也就是修改的代价，比如 你窗子是扣死在墙里的 那么你修改窗子 就必须修改墙 这就比较紧密了，但是如果你窗子是按照某种规格的 可以自由拆装的 那么修改的代价就小，耦合度也就低了。