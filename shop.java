import java.util.HashMap;

class OutofStockException extends Exception {
  OutofStockException(String message) {
    super(message);
  }
}

class shopImplementation {
  private HashMap<String, Integer> products = new HashMap<>();

  public void stock(String name, Integer quantity) {
    synchronized (this) {
      if (products.containsKey(name)) {
        System.out.println("ADDING: Existing " + name + " in stock is " + products.get(name));
        products.replace(name, products.get(name) + quantity);
      } else {
        System.out.println("ADDING: no " + name + " in stock");
        products.put(name, quantity);
      }
      System.out.println("ADDING: " + name + " in stock : " + products.get(name));
      System.out.println();
    }
  }

  private void replaceProductQuantity(String name, Integer quantity) throws OutofStockException {
    if (products.get(name) >= quantity) {
      products.replace(name, products.get(name) - quantity);
    } else {
      throw new OutofStockException("EXCEPTION: BUYING: Only " + quantity + " " + name + " left in stock. required = " + quantity);
    }
  }

  public void sell(String name, Integer quantity) {
    synchronized (this) {
      if (products.containsKey(name)) {
        System.out.println("BUYING: Existing " + name + " in stock is " + products.get(name));

        try {
          replaceProductQuantity(name, quantity);
          System.out.println("BUYING: " + name + " left in stock is " + products.get(name));
        } catch (Exception e) {
          System.out.println(e.getLocalizedMessage());
        }
      } else {
        System.out.println("BUYING: product not found");
      }
      System.out.println();
    }
  }
}

class Add implements Runnable {
  shopImplementation shop = null;
  String name;
  Integer quantity;

  public Add(shopImplementation shop, String name, Integer quantity) {
    this.shop = shop;
    this.name = name;
    this.quantity = quantity;
  }

  @Override
  public void run() {
    shop.stock(name, quantity);
  }
}

class Buy implements Runnable {
  shopImplementation shop = null;
  String name;
  Integer quantity;

  public Buy(shopImplementation shop, String name, Integer quantity) {
    this.shop = shop;
    this.name = name;
    this.quantity = quantity;
  }

  @Override
  public void run() {
    shop.sell(name, quantity);
  }
}

public class shop {
  public static void main(String[] args) {
    shopImplementation shop = new shopImplementation();

    Thread wholeseller1 = new Thread(new Add(shop, "book", 3));
    wholeseller1.start();
    Thread wholeseller2 = new Thread(new Add(shop, "book", 2));
    wholeseller2.start();
    Thread wholeseller3 = new Thread(new Add(shop, "pen", 4));
    wholeseller3.start();
    Thread wholeseller4 = new Thread(new Add(shop, "notes", 3));
    wholeseller4.start();

    try {
      Thread.sleep(5000);
      System.out.println("All stocks updated\n");
    } catch (InterruptedException e) {
      System.out.println(Thread.currentThread().getName() + " : " + e.getMessage());
    }

    Thread buyer1 = new Thread(new Buy(shop, "pen", 2));
    buyer1.start();
    Thread buyer2 = new Thread(new Buy(shop, "book", 4));
    buyer2.start();
    Thread buyer3 = new Thread(new Buy(shop, "book", 3));
    buyer3.start();
    Thread buyer4 = new Thread(new Buy(shop, "notes", 4));
    buyer4.start();
  }
}
