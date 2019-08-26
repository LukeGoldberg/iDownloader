package org.lashly.domain;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Semaphore;

/**
 * custom <code>InputStream</code> for throttle 
 */
public class ThrottlingInputStream extends InputStream {

	private static final int ONE_MB = 1024 * 1024;
	
	private final InputStream target;
	private final Semaphore maxBytesPerSecond;
	
	public ThrottlingInputStream(InputStream target) {
		this.target = target;
		maxBytesPerSecond = new Semaphore(ONE_MB);
	}
	
	public ThrottlingInputStream(InputStream target, int throttlingNumber) {
		this.target = target;
		maxBytesPerSecond = new Semaphore(throttlingNumber);
	}
	
	@Override
	public int read() throws IOException {
		int result;
		try {
		    maxBytesPerSecond.acquire(1);
		    result = target.read();
		} catch (InterruptedException e) {
			// cause this exception is from Semaphore.acquiry(..),
			// ignore throttle when is interrupted.
			result = target.read();
		} finally {
			maxBytesPerSecond.release(1);
		}
		return result;
	}

	@Override
	public int read(byte[] b) throws IOException {
		int result;
		try {
		    maxBytesPerSecond.acquire(b.length);
		    result = target.read(b);
		} catch(InterruptedException e) {
			result = target.read(b);
		} finally {
			maxBytesPerSecond.release(b.length);
		}
		return result;
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		int result;
		try {
			maxBytesPerSecond.acquire(len);
			result = target.read(b, off, len);
		} catch(InterruptedException e) {
			result = target.read(b, off, len);
		} finally {
			maxBytesPerSecond.release(len);
		}
		return result;
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
