import java.util.Timer;
import java.util.TimerTask;
import java.util.function.*;

public abstract class Observable {

  public Observable map(Function fn) {
    var currentObservable = this;
    return new Observable() {
      public void subscribe(Consumer f) {
        currentObservable.subscribe(x -> {
          f.accept(fn.apply(x));
        });
      }
    };
  }

  public Observable filter(Predicate p) {
    var currentObservable = this;
    return new Observable() {
      public void subscribe(Consumer f) {
        currentObservable.subscribe(x -> {
          if (p.test(x)) {
            f.accept(x);
          }
          ;
        });
      }
    };
  }


  public abstract void subscribe(Consumer f);
}
