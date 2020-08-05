# 一、同步调用
http://localhost:28080/doSyncTask

在浏览器访问以上地址，返回内容：
> consume 5006 ms

doSyncTask 为同步方法调用，里边依次执行两个方法，一个耗时2秒，一个耗时3秒，因此总共的时长为5秒左右。

# 二、异步调用

http://localhost:28080/doAsyncTask

在浏览器访问以上地址，返回内容：
> consume 8 ms

doAsyncTask 为异步方法调用，里边依次执行两个方法，一个耗时2秒，一个耗时3秒，因为这两个方法是在另外的线程中执行，主线程会立即返回，所以耗时非常短，在毫秒级别。

# 三、异步调用（带返回值）

http://localhost:28080/doAsyncReturnTask

在浏览器访问以上地址，返回内容：
> consume 3038 ms, task1: task1 finish!， task2: task2 finish!

doAsyncReturnTask 为异步方法调用，里边依次执行两个方法，一个耗时2秒，一个耗时3秒，这两个方法是在另外的线程中执行并返回Future类型的结果，主线程等待异步线程执行完成，所以耗时为两个方法的最大值，在3秒左右。
