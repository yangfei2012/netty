
**注意,当前的版本(fei)人为屏蔽了一些代码从而保证能运行测试用例，要看完整版，请参照master分支**

个人理解：
    参看ProcessOn上的图解。


关于异步：
    (参看Netty in Action5)
    异步有两种实现：
        callback
        Future : like tornado yield

    netty这里采用两种的混合实现(mixture)

        1. ChannelFuture f = b.bind(PORT).sync();  // Future

        2. regFuture.addListener(new ChannelFutureListener ...) // Callback (ChannelFutureListener)


ChannelHandler:
    note that a ChannelHandler instance can be added to more than one ChannelPipeline.
    It means a single ChannelHandler instance can have more than one ChannelHandlerContext
    and therefore the single instance can be invoked with different ChannelHandlerContexts
    if it is added to one or more ChannelPipelines more than once.


ChannelHandlerContext:

    Inbound event传播的方法 :
    ChannelHandlerContext.fireChannelRegistered()
    ChannelHandlerContext.fireChannelActive()
    ChannelHandlerContext.fireChannelRead(Object)
    ChannelHandlerContext.fireChannelReadComplete()
    ChannelHandlerContext.fireExceptionCaught(Throwable)
    ChannelHandlerContext.fireUserEventTriggered(Object)
    ChannelHandlerContext.fireChannelWritabilityChanged()
    ChannelHandlerContext.fireChannelInactive()

    Outbound event 传播的方法 :
    ChannelHandlerContext.bind(SocketAddress, ChannelPromise)
    ChannelHandlerContext.connect(SocketAddress, SocketAddress, ChannelPromise)
    ChannelHandlerContext.write(Object, ChannelPromise)
    ChannelHandlerContext.flush()
    ChannelHandlerContext.read()
    ChannelHandlerContext.disconnect(ChannelPromise)
    ChannelHandlerContext.close(ChannelPromise)


在调用ctx.write(Object)后需要调用ctx.flush()方法，这样才能将数据发出去。
或者直接调用 ctx.writeAndFlush(msg)方法。

通常使用这种方式来实例化ByteBuf：final ByteBuf time = ctx.alloc().buffer(4); ，
而不是直接使用ByteBuf子类的构造方法.


另外，还需要在处理基于流的传输协议TCP/IP的数据时，注意报文和业务程序实际能够接收到的数据之间的关系:
    假如你发送了2个报文，底层是发送了两组字节。
    但是操作系统的TCP栈是有缓存的，它可能把这两组字节合并成一组字节，然后再给业务程序使用。
    但是业务程序往往需要根据把这一组字节还原成原来的两组字节，但是不幸的是，业务程序往往无法直接还原，
    除非在报文上做了些特殊的约定。比如报文是定长的或者有明确的分隔符。