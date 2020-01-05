package org.lashly.domain;

import com.revinate.guava.util.concurrent.RateLimiter;

import java.io.IOException;
import java.io.InputStream;

/**
 * custom <code>InputStream</code> for throttle 
 */
public class ThrottlingInputStream extends InputStream {

	private static final int ONE_MB = 1024 * 1024;

	private static final int ONE_KB = 1024;
	
	private final InputStream target;
	private final RateLimiter maxBytesPerSecond;
	
	public ThrottlingInputStream(InputStream target) {
		this.target = target;
		maxBytesPerSecond = RateLimiter.create(ONE_MB);
	}
	
	public ThrottlingInputStream(InputStream target, int throttlingNumber) {
		this.target = target;
		maxBytesPerSecond = RateLimiter.create(throttlingNumber * ONE_KB);
	}
	
	@Override
	public int read() throws IOException {
	    maxBytesPerSecond.acquire(1);
        return target.read();
	}

	@Override
	public int read(byte[] b) throws IOException {
		maxBytesPerSecond.acquire(b.length);
	    return target.read(b);
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
        maxBytesPerSecond.acquire(len);
	    return target.read(b, off, len);
	}

	@Override
	public long skip(long n) throws IOException {
		return target.skip(n);
	}

	@Override
	public int available() throws IOException {
		return target.available();
	}

	@Override
	public synchronized void mark(int readlimit) {
		target.mark(readlimit);
	}

	@Override
	public synchronized void reset() throws IOException {
		target.reset();
	}

	@Override
	public boolean markSupported() {
		return target.markSupported();
	}

	@Override
	public void close() throws IOException {
		target.close();
	}

}
