package educativeio.practiceproblems;

/**
 * @author sm@creativefusion.net
 */
public class RateLimitingTokenBucket {

    Token[] bucket;
    int max;
    int size;

    public RateLimitingTokenBucket(int max) {
        this.bucket = new Token[max];
        this.max = max;
        this.size = 0;
        new Thread(r).start();
    }

    Runnable r = () -> {
        while(true) {
            try {
                this.addToken();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    };

    private void addToken() throws InterruptedException {
        synchronized (this) {
            while (size == max-1)
                this.wait();

            bucket[size] = new Token();
            size++;
            notifyAll();

            System.out.println("Adding a token - bucket size: " + size);
        }
    }

    public Token getToken() throws InterruptedException {
        synchronized (this) {
            while (size == 0)
                this.wait();

            Token token = bucket[size];
            size--;
            notifyAll();

            System.out.println("Got a token - bucket size: " + size);
            return token;
        }

    }
}

class Token {

}

class Test {
    public static void main(String[] args) {
        RateLimitingTokenBucket bucket = new RateLimitingTokenBucket(5);
        new Thread(() -> {
            while(true) {
                try {
                    Token t = bucket.getToken();
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}