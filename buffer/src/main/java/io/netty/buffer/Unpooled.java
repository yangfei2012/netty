/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.netty.buffer;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.ReadOnlyBufferException;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


/**
 * Creates a new {@link ByteBuf} or a new {@link MessageBuf} by allocating new space or by wrapping
 * or copying existing byte arrays, byte buffers and a string.
 *
 * <h3>Use static import</h3>
 * This classes is intended to be used with Java 5 static import statement:
 *
 * <pre>
 * import static io.netty.buffer.{@link Unpooled}.*;
 *
 * {@link ByteBuf} heapBuffer    = buffer(128);
 * {@link ByteBuf} directBuffer  = directBuffer(256);
 * {@link ByteBuf} wrappedBuffer = wrappedBuffer(new byte[128], new byte[256]);
 * {@link ByteBuf} copiedBuffe r = copiedBuffer({@link ByteBuffer}.allocate(128));
 * </pre>
 *
 * <h3>Allocating a new buffer</h3>
 *
 * Three buffer types are provided out of the box.
 *
 * <ul>
 * <li>{@link #buffer(int)} allocates a new fixed-capacity heap buffer.</li>
 * <li>{@link #directBuffer(int)} allocates a new fixed-capacity direct buffer.</li>
 * </ul>
 *
 * <h3>Creating a wrapped buffer</h3>
 *
 * Wrapped buffer is a buffer which is a view of one or more existing
 * byte arrays and byte buffers.  Any changes in the content of the original
 * array or buffer will be visible in the wrapped buffer.  Various wrapper
 * methods are provided and their name is all {@code wrappedBuffer()}.
 * You might want to take a look at the methods that accept varargs closely if
 * you want to create a buffer which is composed of more than one array to
 * reduce the number of memory copy.
 *
 * <h3>Creating a copied buffer</h3>
 *
 * Copied buffer is a deep copy of one or more existing byte arrays, byte
 * buffers or a string.  Unlike a wrapped buffer, there's no shared data
 * between the original data and the copied buffer.  Various copy methods are
 * provided and their name is all {@code copiedBuffer()}.  It is also convenient
 * to use this operation to merge multiple buffers into one buffer.
 *
 * <h3>Miscellaneous utility methods</h3>
 *
 * This class also provides various utility methods to help implementation
 * of a new buffer type, generation of hex dump and swapping an integer's
 * byte order.
 * @apiviz.landmark
 * @apiviz.has io.netty.buffer.Buf oneway - - creates
 */
public final class Unpooled {

    private static final ByteBufAllocator ALLOC = UnpooledByteBufAllocator.HEAP_BY_DEFAULT;

    /**
     * Big endian byte order.
     */
    public static final ByteOrder BIG_ENDIAN = ByteOrder.BIG_ENDIAN;

    /**
     * Little endian byte order.
     */
    public static final ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;

    /**
     * A buffer whose capacity is {@code 0}.
     */
    public static final ByteBuf EMPTY_BUFFER = new AbstractByteBuf(0) {
        @Override
        public int capacity() {
            return 0;
        }

        @Override
        public ByteBuf capacity(int newCapacity) {
            throw new ReadOnlyBufferException();
        }

        @Override
        public ByteBufAllocator alloc() {
            return ALLOC;
        }

        @Override
        public ByteOrder order() {
            return BIG_ENDIAN;
        }

        @Override
        public ByteBuf unwrap() {
            return null;
        }

        @Override
        public boolean isDirect() {
            return false;
        }

        @Override
        public byte getByte(int index) {
            throw new IndexOutOfBoundsException();
        }

        @Override
        public short getShort(int index) {
            throw new IndexOutOfBoundsException();
        }

        @Override
        public int getUnsignedMedium(int index) {
            throw new IndexOutOfBoundsException();
        }

        @Override
        public int getInt(int index) {
            throw new IndexOutOfBoundsException();
        }

        @Override
        public long getLong(int index) {
            throw new IndexOutOfBoundsException();
        }

        @Override
        public ByteBuf getBytes(int index, ByteBuf dst, int dstIndex, int length) {
            throw new IndexOutOfBoundsException();
        }

        @Override
        public ByteBuf getBytes(int index, byte[] dst, int dstIndex, int length) {
            throw new IndexOutOfBoundsException();
        }

        @Override
        public ByteBuf getBytes(int index, ByteBuffer dst) {
            throw new IndexOutOfBoundsException();
        }

        @Override
        public ByteBuf getBytes(int index, OutputStream out, int length) {
            throw new IndexOutOfBoundsException();
        }

        @Override
        public int getBytes(int index, GatheringByteChannel out, int length) {
            throw new IndexOutOfBoundsException();
        }

        @Override
        public ByteBuf setByte(int index, int value) {
            throw new ReadOnlyBufferException();
        }

        @Override
        public ByteBuf setShort(int index, int value) {
            throw new ReadOnlyBufferException();
        }

        @Override
        public ByteBuf setMedium(int index, int value) {
            throw new ReadOnlyBufferException();
        }

        @Override
        public ByteBuf setInt(int index, int value) {
            throw new ReadOnlyBufferException();
        }

        @Override
        public ByteBuf setLong(int index, long value) {
            throw new ReadOnlyBufferException();
        }

        @Override
        public ByteBuf setBytes(int index, ByteBuf src, int srcIndex, int length) {
            throw new ReadOnlyBufferException();
        }

        @Override
        public ByteBuf setBytes(int index, byte[] src, int srcIndex, int length) {
            throw new ReadOnlyBufferException();
        }

        @Override
        public ByteBuf setBytes(int index, ByteBuffer src) {
            throw new ReadOnlyBufferException();
        }

        @Override
        public int setBytes(int index, InputStream in, int length) {
            throw new ReadOnlyBufferException();
        }

        @Override
        public int setBytes(int index, ScatteringByteChannel in, int length) {
            throw new ReadOnlyBufferException();
        }

        @Override
        public ByteBuf copy(int index, int length) {
            throw new IndexOutOfBoundsException();
        }

        @Override
        public int nioBufferCount() {
            return 0;
        }

        @Override
        public ByteBuffer nioBuffer(int index, int length) {
            return ByteBuffer.allocate(0);
        }

        @Override
        public ByteBuffer[] nioBuffers(int index, int length) {
            return new ByteBuffer[0];
        }

        @Override
        public boolean hasArray() {
            return true;
        }

        @Override
        public byte[] array() {
            return new byte[0];
        }

        @Override
        public int arrayOffset() {
            return 0;
        }

        @Override
        public ByteBuf suspendIntermediaryDeallocations() {
            return this;
        }

        @Override
        public ByteBuf resumeIntermediaryDeallocations() {
            return this;
        }

        @Override
        public void free() {
            // do nothing
            // TODO: Maybe throw an UnsupportedOperationException
        }

        @Override
        public boolean isFreed() {
            return false;
        }
    };

    public static <T> MessageBuf<T> messageBuffer() {
        return new DefaultMessageBuf<T>();
    }

    public static <T> MessageBuf<T> messageBuffer(int initialCapacity) {
        return new DefaultMessageBuf<T>(initialCapacity);
    }

    public static <T> MessageBuf<T> wrappedBuffer(Queue<T> queue) {
        if (queue instanceof MessageBuf) {
            return (MessageBuf<T>) queue;
        }
        return new QueueBackedMessageBuf<T>(queue);
    }

    /**
     * Creates a new big-endian Java heap buffer with reasonably small initial capacity, which
     * expands its capacity boundlessly on demand.
     */
    public static ByteBuf buffer() {
        return ALLOC.heapBuffer();
    }

    /**
     * Creates a new big-endian direct buffer with resaonably small initial capacity, which
     * expands its capacity boundlessly on demand.
     */
    public static ByteBuf directBuffer() {
        return ALLOC.directBuffer();
    }

    /**
     * Creates a new big-endian Java heap buffer with the specified {@code capacity}, which
     * expands its capacity boundlessly on demand.  The new buffer's {@code readerIndex} and
     * {@code writerIndex} are {@code 0}.
     */
    public static ByteBuf buffer(int initialCapacity) {
        return ALLOC.heapBuffer(initialCapacity);
    }

    /**
     * Creates a new big-endian direct buffer with the specified {@code capacity}, which
     * expands its capacity boundlessly on demand.  The new buffer's {@code readerIndex} and
     * {@code writerIndex} are {@code 0}.
     */
    public static ByteBuf directBuffer(int initialCapacity) {
        return ALLOC.directBuffer(initialCapacity);
    }

    /**
     * Creates a new big-endian Java heap buffer with the specified
     * {@code capacity}.  The new buffer's {@code readerIndex} and
     * {@code writerIndex} are {@code 0}.
     */
    public static ByteBuf buffer(int initialCapacity, int maxCapacity) {
        return ALLOC.heapBuffer(initialCapacity, maxCapacity);
    }

    /**
     * Creates a new big-endian direct buffer with the specified
     * {@code capacity}.  The new buffer's {@code readerIndex} and
     * {@code writerIndex} are {@code 0}.
     */
    public static ByteBuf directBuffer(int initialCapacity, int maxCapacity) {
        return ALLOC.directBuffer(initialCapacity, maxCapacity);
    }

    /**
     * Creates a new big-endian buffer which wraps the specified {@code array}.
     * A modification on the specified array's content will be visible to the
     * returned buffer.
     */
    public static ByteBuf wrappedBuffer(byte[] array) {
        if (array.length == 0) {
            return EMPTY_BUFFER;
        }
        return new UnpooledHeapByteBuf(ALLOC, array, array.length);
    }

    /**
     * Creates a new big-endian buffer which wraps the sub-region of the
     * specified {@code array}.  A modification on the specified array's
     * content will be visible to the returned buffer.
     */
    public static ByteBuf wrappedBuffer(byte[] array, int offset, int length) {
        if (length == 0) {
            return EMPTY_BUFFER;
        }

        if (offset == 0 && length == array.length) {
            return wrappedBuffer(array);
        }

        return wrappedBuffer(array).slice(offset, length);
    }

    /**
     * Creates a new buffer which wraps the specified NIO buffer's current
     * slice.  A modification on the specified buffer's content will be
     * visible to the returned buffer.
     */
    public static ByteBuf wrappedBuffer(ByteBuffer buffer) {
        if (!buffer.hasRemaining()) {
            return EMPTY_BUFFER;
        }
        if (buffer.hasArray()) {
            return wrappedBuffer(
                    buffer.array(),
                    buffer.arrayOffset() + buffer.position(),
                    buffer.remaining()).order(buffer.order());
        } else {
            return new UnpooledDirectByteBuf(ALLOC, buffer, buffer.remaining());
        }
    }

    /**
     * Creates a new buffer which wraps the specified buffer's readable bytes.
     * A modification on the specified buffer's content will be visible to the
     * returned buffer.
     */
    public static ByteBuf wrappedBuffer(ByteBuf buffer) {
        if (buffer.readable()) {
            return buffer.slice();
        } else {
            return EMPTY_BUFFER;
        }
    }

    /**
     * Creates a new big-endian composite buffer which wraps the specified
     * arrays without copying them.  A modification on the specified arrays'
     * content will be visible to the returned buffer.
     */
    public static ByteBuf wrappedBuffer(byte[]... arrays) {
        return wrappedBuffer(16, arrays);
    }

    /**
     * Creates a new big-endian composite buffer which wraps the readable bytes of the
     * specified buffers without copying them.  A modification on the content
     * of the specified buffers will be visible to the returned buffer.
     */
    public static ByteBuf wrappedBuffer(ByteBuf... buffers) {
        return wrappedBuffer(16, buffers);
    }

    /**
     * Creates a new big-endian composite buffer which wraps the slices of the specified
     * NIO buffers without copying them.  A modification on the content of the
     * specified buffers will be visible to the returned buffer.
     */
    public static ByteBuf wrappedBuffer(ByteBuffer... buffers) {
        return wrappedBuffer(16, buffers);
    }

    /**
     * Creates a new big-endian composite buffer which wraps the specified
     * arrays without copying them.  A modification on the specified arrays'
     * content will be visible to the returned buffer.
     */
    public static ByteBuf wrappedBuffer(int maxNumComponents, byte[]... arrays) {
        switch (arrays.length) {
        case 0:
            break;
        case 1:
            if (arrays[0].length != 0) {
                return wrappedBuffer(arrays[0]);
            }
            break;
        default:
            // Get the list of the component, while guessing the byte order.
            final List<ByteBuf> components = new ArrayList<ByteBuf>(arrays.length);
            for (byte[] a: arrays) {
                if (a == null) {
                    break;
                }
                if (a.length > 0) {
                    components.add(wrappedBuffer(a));
                }
            }

            if (!components.isEmpty()) {
                return new DefaultCompositeByteBuf(ALLOC, false, maxNumComponents, components);
            }
        }

        return EMPTY_BUFFER;
    }

    /**
     * Creates a new big-endian composite buffer which wraps the readable bytes of the
     * specified buffers without copying them.  A modification on the content
     * of the specified buffers will be visible to the returned buffer.
     */
    public static ByteBuf wrappedBuffer(int maxNumComponents, ByteBuf... buffers) {
        switch (buffers.length) {
        case 0:
            break;
        case 1:
            if (buffers[0].readable()) {
                return wrappedBuffer(buffers[0].order(BIG_ENDIAN));
            }
            break;
        default:
            for (ByteBuf b: buffers) {
                if (b.readable()) {
                    return new DefaultCompositeByteBuf(ALLOC, false, maxNumComponents, buffers);
                }
            }
        }
        return EMPTY_BUFFER;
    }

    /**
     * Creates a new big-endian composite buffer which wraps the slices of the specified
     * NIO buffers without copying them.  A modification on the content of the
     * specified buffers will be visible to the returned buffer.
     */
    public static ByteBuf wrappedBuffer(int maxNumComponents, ByteBuffer... buffers) {
        switch (buffers.length) {
        case 0:
            break;
        case 1:
            if (buffers[0].hasRemaining()) {
                return wrappedBuffer(buffers[0].order(BIG_ENDIAN));
            }
            break;
        default:
            // Get the list of the component, while guessing the byte order.
            final List<ByteBuf> components = new ArrayList<ByteBuf>(buffers.length);
            for (ByteBuffer b: buffers) {
                if (b == null) {
                    break;
                }
                if (b.remaining() > 0) {
                    components.add(wrappedBuffer(b.order(BIG_ENDIAN)));
                }
            }

            if (!components.isEmpty()) {
                return new DefaultCompositeByteBuf(ALLOC, false, maxNumComponents, components);
            }
        }

        return EMPTY_BUFFER;
    }

    /**
     * Returns a new big-endian composite buffer with no components.
     */
    public static CompositeByteBuf compositeBuffer() {
        return compositeBuffer(16);
    }

    /**
     * Returns a new big-endian composite buffer with no components.
     */
    public static CompositeByteBuf compositeBuffer(int maxNumComponents) {
        return new DefaultCompositeByteBuf(ALLOC, false, maxNumComponents);
    }

    /**
     * Creates a new big-endian buffer whose content is a copy of the
     * specified {@code array}.  The new buffer's {@code readerIndex} and
     * {@code writerIndex} are {@code 0} and {@code array.length} respectively.
     */
    public static ByteBuf copiedBuffer(byte[] array) {
        if (array.length == 0) {
            return EMPTY_BUFFER;
        }
        return wrappedBuffer(array.clone());
    }

    /**
     * Creates a new big-endian buffer whose content is a copy of the
     * specified {@code array}'s sub-region.  The new buffer's
     * {@code readerIndex} and {@code writerIndex} are {@code 0} and
     * the specified {@code length} respectively.
     */
    public static ByteBuf copiedBuffer(byte[] array, int offset, int length) {
        if (length == 0) {
            return EMPTY_BUFFER;
        }
        byte[] copy = new byte[length];
        System.arraycopy(array, offset, copy, 0, length);
        return wrappedBuffer(copy);
    }

    /**
     * Creates a new buffer whose content is a copy of the specified
     * {@code buffer}'s current slice.  The new buffer's {@code readerIndex}
     * and {@code writerIndex} are {@code 0} and {@code buffer.remaining}
     * respectively.
     */
    public static ByteBuf copiedBuffer(ByteBuffer buffer) {
        int length = buffer.remaining();
        if (length == 0) {
            return EMPTY_BUFFER;
        }
        byte[] copy = new byte[length];
        int position = buffer.position();
        try {
            buffer.get(copy);
        } finally {
            buffer.position(position);
        }
        return wrappedBuffer(copy).order(buffer.order());
    }

    /**
     * Creates a new buffer whose content is a copy of the specified
     * {@code buffer}'s readable bytes.  The new buffer's {@code readerIndex}
     * and {@code writerIndex} are {@code 0} and {@code buffer.readableBytes}
     * respectively.
     */
    public static ByteBuf copiedBuffer(ByteBuf buffer) {
        if (buffer.readable()) {
            return buffer.copy();
        } else {
            return EMPTY_BUFFER;
        }
    }

    /**
     * Creates a new big-endian buffer whose content is a merged copy of
     * the specified {@code arrays}.  The new buffer's {@code readerIndex}
     * and {@code writerIndex} are {@code 0} and the sum of all arrays'
     * {@code length} respectively.
     */
    public static ByteBuf copiedBuffer(byte[]... arrays) {
        switch (arrays.length) {
        case 0:
            return EMPTY_BUFFER;
        case 1:
            if (arrays[0].length == 0) {
                return EMPTY_BUFFER;
            } else {
                return copiedBuffer(arrays[0]);
            }
        }

        // Merge the specified arrays into one array.
        int length = 0;
        for (byte[] a: arrays) {
            if (Integer.MAX_VALUE - length < a.length) {
                throw new IllegalArgumentException(
                        "The total length of the specified arrays is too big.");
            }
            length += a.length;
        }

        if (length == 0) {
            return EMPTY_BUFFER;
        }

        byte[] mergedArray = new byte[length];
        for (int i = 0, j = 0; i < arrays.length; i ++) {
            byte[] a = arrays[i];
            System.arraycopy(a, 0, mergedArray, j, a.length);
            j += a.length;
        }

        return wrappedBuffer(mergedArray);
    }

    /**
     * Creates a new buffer whose content is a merged copy of the specified
     * {@code buffers}' readable bytes.  The new buffer's {@code readerIndex}
     * and {@code writerIndex} are {@code 0} and the sum of all buffers'
     * {@code readableBytes} respectively.
     *
     * @throws IllegalArgumentException
     *         if the specified buffers' endianness are different from each
     *         other
     */
    public static ByteBuf copiedBuffer(ByteBuf... buffers) {
        switch (buffers.length) {
        case 0:
            return EMPTY_BUFFER;
        case 1:
            return copiedBuffer(buffers[0]);
        }

        // Merge the specified buffers into one buffer.
        ByteOrder order = null;
        int length = 0;
        for (ByteBuf b: buffers) {
            int bLen = b.readableBytes();
            if (bLen <= 0) {
                continue;
            }
            if (Integer.MAX_VALUE - length < bLen) {
                throw new IllegalArgumentException(
                        "The total length of the specified buffers is too big.");
            }
            length += bLen;
            if (order != null) {
                if (!order.equals(b.order())) {
                    throw new IllegalArgumentException("inconsistent byte order");
                }
            } else {
                order = b.order();
            }
        }

        if (length == 0) {
            return EMPTY_BUFFER;
        }

        byte[] mergedArray = new byte[length];
        for (int i = 0, j = 0; i < buffers.length; i ++) {
            ByteBuf b = buffers[i];
            int bLen = b.readableBytes();
            b.getBytes(b.readerIndex(), mergedArray, j, bLen);
            j += bLen;
        }

        return wrappedBuffer(mergedArray).order(order);
    }

    /**
     * Creates a new buffer whose content is a merged copy of the specified
     * {@code buffers}' slices.  The new buffer's {@code readerIndex} and
     * {@code writerIndex} are {@code 0} and the sum of all buffers'
     * {@code remaining} respectively.
     *
     * @throws IllegalArgumentException
     *         if the specified buffers' endianness are different from each
     *         other
     */
    public static ByteBuf copiedBuffer(ByteBuffer... buffers) {
        switch (buffers.length) {
        case 0:
            return EMPTY_BUFFER;
        case 1:
            return copiedBuffer(buffers[0]);
        }

        // Merge the specified buffers into one buffer.
        ByteOrder order = null;
        int length = 0;
        for (ByteBuffer b: buffers) {
            int bLen = b.remaining();
            if (bLen <= 0) {
                continue;
            }
            if (Integer.MAX_VALUE - length < bLen) {
                throw new IllegalArgumentException(
                        "The total length of the specified buffers is too big.");
            }
            length += bLen;
            if (order != null) {
                if (!order.equals(b.order())) {
                    throw new IllegalArgumentException("inconsistent byte order");
                }
            } else {
                order = b.order();
            }
        }

        if (length == 0) {
            return EMPTY_BUFFER;
        }

        byte[] mergedArray = new byte[length];
        for (int i = 0, j = 0; i < buffers.length; i ++) {
            ByteBuffer b = buffers[i];
            int bLen = b.remaining();
            int oldPos = b.position();
            b.get(mergedArray, j, bLen);
            b.position(oldPos);
            j += bLen;
        }

        return wrappedBuffer(mergedArray).order(order);
    }

    /**
     * Creates a new big-endian buffer whose content is the specified
     * {@code string} encoded in the specified {@code charset}.
     * The new buffer's {@code readerIndex} and {@code writerIndex} are
     * {@code 0} and the length of the encoded string respectively.
     */
    public static ByteBuf copiedBuffer(CharSequence string, Charset charset) {
        if (string == null) {
            throw new NullPointerException("string");
        }

        if (string instanceof CharBuffer) {
            return copiedBuffer((CharBuffer) string, charset);
        }

        return copiedBuffer(CharBuffer.wrap(string), charset);
    }

    /**
     * Creates a new big-endian buffer whose content is a subregion of
     * the specified {@code string} encoded in the specified {@code charset}.
     * The new buffer's {@code readerIndex} and {@code writerIndex} are
     * {@code 0} and the length of the encoded string respectively.
     */
    public static ByteBuf copiedBuffer(
            CharSequence string, int offset, int length, Charset charset) {
        if (string == null) {
            throw new NullPointerException("string");
        }
        if (length == 0) {
            return EMPTY_BUFFER;
        }

        if (string instanceof CharBuffer) {
            CharBuffer buf = (CharBuffer) string;
            if (buf.hasArray()) {
                return copiedBuffer(
                        buf.array(),
                        buf.arrayOffset() + buf.position() + offset,
                        length, charset);
            }

            buf = buf.slice();
            buf.limit(length);
            buf.position(offset);
            return copiedBuffer(buf, charset);
        }

        return copiedBuffer(CharBuffer.wrap(string, offset, offset + length), charset);
    }

    /**
     * Creates a new big-endian buffer whose content is the specified
     * {@code array} encoded in the specified {@code charset}.
     * The new buffer's {@code readerIndex} and {@code writerIndex} are
     * {@code 0} and the length of the encoded string respectively.
     */
    public static ByteBuf copiedBuffer(char[] array, Charset charset) {
        return copiedBuffer(array, 0, array.length, charset);
    }

    /**
     * Creates a new big-endian buffer whose content is a subregion of
     * the specified {@code array} encoded in the specified {@code charset}.
     * The new buffer's {@code readerIndex} and {@code writerIndex} are
     * {@code 0} and the length of the encoded string respectively.
     */
    public static ByteBuf copiedBuffer(char[] array, int offset, int length, Charset charset) {
        if (array == null) {
            throw new NullPointerException("array");
        }
        if (length == 0) {
            return EMPTY_BUFFER;
        }
        return copiedBuffer(CharBuffer.wrap(array, offset, length), charset);
    }

    private static ByteBuf copiedBuffer(CharBuffer buffer, Charset charset) {
        ByteBuffer dst = ByteBufUtil.encodeString(buffer, charset);
        ByteBuf result = wrappedBuffer(dst.array());
        result.writerIndex(dst.remaining());
        return result;
    }

    /**
     * Creates a read-only buffer which disallows any modification operations
     * on the specified {@code buffer}.  The new buffer has the same
     * {@code readerIndex} and {@code writerIndex} with the specified
     * {@code buffer}.
     */
    public static ByteBuf unmodifiableBuffer(ByteBuf buffer) {
        ByteOrder endianness = buffer.order();
        if (endianness == BIG_ENDIAN) {
            return new ReadOnlyByteBuf(buffer);
        }

        return new ReadOnlyByteBuf(buffer.order(BIG_ENDIAN)).order(LITTLE_ENDIAN);
    }

    /**
     * Creates a new 4-byte big-endian buffer that holds the specified 32-bit integer.
     */
    public static ByteBuf copyInt(int value) {
        ByteBuf buf = buffer(4);
        buf.writeInt(value);
        return buf;
    }

    /**
     * Create a big-endian buffer that holds a sequence of the specified 32-bit integers.
     */
    public static ByteBuf copyInt(int... values) {
        if (values == null || values.length == 0) {
            return EMPTY_BUFFER;
        }
        ByteBuf buffer = buffer(values.length * 4);
        for (int v: values) {
            buffer.writeInt(v);
        }
        return buffer;
    }

    /**
     * Creates a new 2-byte big-endian buffer that holds the specified 16-bit integer.
     */
    public static ByteBuf copyShort(int value) {
        ByteBuf buf = buffer(2);
        buf.writeShort(value);
        return buf;
    }

    /**
     * Create a new big-endian buffer that holds a sequence of the specified 16-bit integers.
     */
    public static ByteBuf copyShort(short... values) {
        if (values == null || values.length == 0) {
            return EMPTY_BUFFER;
        }
        ByteBuf buffer = buffer(values.length * 2);
        for (int v: values) {
            buffer.writeShort(v);
        }
        return buffer;
    }

    /**
     * Create a new big-endian buffer that holds a sequence of the specified 16-bit integers.
     */
    public static ByteBuf copyShort(int... values) {
        if (values == null || values.length == 0) {
            return EMPTY_BUFFER;
        }
        ByteBuf buffer = buffer(values.length * 2);
        for (int v: values) {
            buffer.writeShort(v);
        }
        return buffer;
    }

    /**
     * Creates a new 3-byte big-endian buffer that holds the specified 24-bit integer.
     */
    public static ByteBuf copyMedium(int value) {
        ByteBuf buf = buffer(3);
        buf.writeMedium(value);
        return buf;
    }

    /**
     * Create a new big-endian buffer that holds a sequence of the specified 24-bit integers.
     */
    public static ByteBuf copyMedium(int... values) {
        if (values == null || values.length == 0) {
            return EMPTY_BUFFER;
        }
        ByteBuf buffer = buffer(values.length * 3);
        for (int v: values) {
            buffer.writeMedium(v);
        }
        return buffer;
    }

    /**
     * Creates a new 8-byte big-endian buffer that holds the specified 64-bit integer.
     */
    public static ByteBuf copyLong(long value) {
        ByteBuf buf = buffer(8);
        buf.writeLong(value);
        return buf;
    }

    /**
     * Create a new big-endian buffer that holds a sequence of the specified 64-bit integers.
     */
    public static ByteBuf copyLong(long... values) {
        if (values == null || values.length == 0) {
            return EMPTY_BUFFER;
        }
        ByteBuf buffer = buffer(values.length * 8);
        for (long v: values) {
            buffer.writeLong(v);
        }
        return buffer;
    }

    /**
     * Creates a new single-byte big-endian buffer that holds the specified boolean value.
     */
    public static ByteBuf copyBoolean(boolean value) {
        ByteBuf buf = buffer(1);
        buf.writeBoolean(value);
        return buf;
    }

    /**
     * Create a new big-endian buffer that holds a sequence of the specified boolean values.
     */
    public static ByteBuf copyBoolean(boolean... values) {
        if (values == null || values.length == 0) {
            return EMPTY_BUFFER;
        }
        ByteBuf buffer = buffer(values.length);
        for (boolean v: values) {
            buffer.writeBoolean(v);
        }
        return buffer;
    }

    /**
     * Creates a new 4-byte big-endian buffer that holds the specified 32-bit floating point number.
     */
    public static ByteBuf copyFloat(float value) {
        ByteBuf buf = buffer(4);
        buf.writeFloat(value);
        return buf;
    }

    /**
     * Create a new big-endian buffer that holds a sequence of the specified 32-bit floating point numbers.
     */
    public static ByteBuf copyFloat(float... values) {
        if (values == null || values.length == 0) {
            return EMPTY_BUFFER;
        }
        ByteBuf buffer = buffer(values.length * 4);
        for (float v: values) {
            buffer.writeFloat(v);
        }
        return buffer;
    }

    /**
     * Creates a new 8-byte big-endian buffer that holds the specified 64-bit floating point number.
     */
    public static ByteBuf copyDouble(double value) {
        ByteBuf buf = buffer(8);
        buf.writeDouble(value);
        return buf;
    }

    /**
     * Create a new big-endian buffer that holds a sequence of the specified 64-bit floating point numbers.
     */
    public static ByteBuf copyDouble(double... values) {
        if (values == null || values.length == 0) {
            return EMPTY_BUFFER;
        }
        ByteBuf buffer = buffer(values.length * 8);
        for (double v: values) {
            buffer.writeDouble(v);
        }
        return buffer;
    }

    private Unpooled() {
        // Unused
    }
}