import java.util.concurrent.Semaphore;


class Parquing implements Runnable {

    private final Semaphore semaforo;
    private final int id;

    public Parquing(Semaphore semaforo, int id){
        this.semaforo = semaforo;
        this.id = id;
    }

    @Override
    public void run() {
        try{
            System.out.println("El coche "+id+ " esta esperando para entrar");
            semaforo.acquire(1);
            System.out.println("El coche "+id+ " ha entrado al parking");
            Thread.sleep((long) (Math.random() * 5000));
            System.out.println("El coche "+id+ " ha salido del parking");
            semaforo.release(1);
        } catch (InterruptedException ie) {
            
        }
    }

}


public class Main { 
    public static void main(String[] args) throws Exception {
        int plazasParking = 3;
        int numeroCoches = 5;

        Semaphore semaforo = new Semaphore(plazasParking);

        for ( int i = 1; i <= numeroCoches; i++){
            Thread coche = new Thread( new Parquing(semaforo, i));
            coche.start();
        }
    }

}

