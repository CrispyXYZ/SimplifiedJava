# SimplifiedJava
## Build
```ruby
git clone https://github.com/CrispyXYZ/SimplifiedJava
cd SimplifiedJava
javac src/Main.java -d .
```
## Run
Usage:
```ruby
java Main <inFile> <outFile> [properties]

* if properties is null, it will use default property
```
e.g.
```ruby
# cat main.sj
im_ ju.Sc_;

$mainClass {
        pri_ $n(){}
        pu_ s_ Sc_ sc = new Sc_(System.in);
        _main {
                forever {
                        println_(sc.n_());
                }
        }
}

# cat myProp.properties
im_=import
ju\\.=java.util.
jl\\.=java.lang.
pri_=private
pro_=protected
pu_=public
s_=static
f_=final
ab_=abstract
sync_=synchronized
tran_=transient
voli_=volatile
const=static final
_main=public static void main(String[] args)
println_=System.out.println
forever=while(true)
Sc_=Scanner
n_=nextLine

# java Main main.sj CJSNS.java myProp.properties

# cat CJSNS.java
import java.util.Scanner;

public class CJSNS {
        private CJSNS(){}
        public static Scanner sc = new Scanner(System.in);
        public static void main(String[] args) {
                while(true) {
                        System.out.println(sc.nextLine());
                }
        }
}
```
