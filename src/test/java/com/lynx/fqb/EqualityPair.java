package com.lynx.fqb;

import java.util.function.Supplier;

public class EqualityPair<T> {

	private final T thisObj;

	private final Object thatObj;

	private final boolean equality;

	public EqualityPair(T thisObj, Object thatObj, boolean equality) {
		this.thisObj = thisObj;
		this.thatObj = thatObj;
		this.equality = equality;
	}

	public T getThisObj() {
		return thisObj;
	}

	public Object getThatObj() {
		return thatObj;
	}

	public boolean isEquality() {
		return equality;
	}

	public static <T> EqualityPair<T> equalPair(T thisObj, T thatObj) {
		return new EqualityPair<>(thisObj, thatObj, true);
	}

	public static <T> EqualityPair<T> equalPair(Supplier<T> thisObj, Supplier<T> thatObj) {
		return new EqualityPair<>(thisObj.get(), thatObj.get(), true);
	}

	public static <T> EqualityPair<T> inequalPair(T thisObj, Object thatObj) {
		return new EqualityPair<>(thisObj, thatObj, false);
	}

	public static <T> EqualityPair<T> inequalPair(Supplier<T> thisObj, Supplier<Object> thatObj) {
		return new EqualityPair<>(thisObj.get(), thatObj.get(), false);
	}

}
