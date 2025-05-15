package br.com.chatapp.model;

public class Result<T, E> {
  private final T value;
  private final E error;
  private final boolean isSuccess;

  private Result(T value, E error, boolean isSuccess) {
    this.value = value;
    this.error = error;
    this.isSuccess = isSuccess;
  }

  public static <T, E> Result<T, E> ok(T value) {
    return new Result<>(value, null, true);
  }

  public static <T, E> Result<T, E> fail(E error) {
    return new Result<>(null, error, false);
  }

  public boolean isSuccess() {
    return this.isSuccess;
  }

  public boolean isFailure() {
    return !this.isSuccess;
  }

  public T getValue() {
    return this.value;
  }

  public E getError() {
    return this.error;
  }
}
