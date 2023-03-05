package interfacePrac;

interface Binter{
}


interface Ainter {
	
}
interface Cinter {
	
}

class Aclass {
	 
 }
 
class Bclass {
	 
 }

class Cclass {
	 
}
abstract class Predator extends Animal {
	abstract String eat();
}

interface Barkable {
    String bark();
}


//interface BarkablePredator extends Predator, Barkable {
//}


class Animal {
    String name;

    void setName(String name) {
        this.name = name;
    }

    
}

class Tiger extends Predator implements Barkable  {
	@Override
	public String eat() {
		return "apple";
		
	}

	@Override
	public String bark() {
		return "어흥";
	}

	
}

class Lion extends Predator implements Barkable {
	@Override
	public String eat() {
		return "banana";
		
	}

	@Override
	public String bark() {
		return "으르렁";
	}
}



class Bouncer {
    void barkAnimal(Barkable bb) {
    	System.out.println(bb.bark());
    }
}
class ZooKeeper {
    void feed(Predator predator) {  // 호랑이가 오면 사과를 던져 준다.
        System.out.println("feed " +predator.eat());
    }
    
    


}

public class C01_Interface {
    public static void main(String[] args) {
        ZooKeeper zooKeeper = new ZooKeeper();
        Tiger tiger = new Tiger();
        Lion lion = new Lion();
        zooKeeper.feed(tiger);  // feed apple 출력
        zooKeeper.feed(lion);  // feed banana 출력
        
        Bouncer bouncer= new Bouncer();
        bouncer.barkAnimal(tiger);
        bouncer.barkAnimal(lion);
    }
}