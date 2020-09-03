# CompilerByRegex-oo6

= File Description =
We have several packages - blocks, file reader, main, method, parser, regex and variable.
it holds all the relevant classes to run the "s-java compiler".
I have chosen to order them under packages by their role in the program so it easy to understand the program's structure.
There is a README as well.

=          Design           =

I chose to "analyze" the s-java files by going over the code two times. The first time, is for "mapping" the code-
separates "Blocks" to global, methods and conditionals: each of them holds the parameters declared in its scope, the
methods, and booleans that indicates its status(isInitialized?, isTrue?) and to checks some part of the code.The second,
is for verify the code carefully, and throw exceptions if the code is illegal.
A crucial part of those two over-code procedures is done by the regex mechanism. We used it to bound scopes, we used it
to validate the legality of the scope. We chose to define a regex class that holds al the expressions, and divides the
expressions to short one, so its more readable and make sense of what match we look for in the code.
So, after "parsing" the scope, we "create" them by factories(there is one for each type) and checks their correctness.
therefore, there can be many of errors types, so each sub-package like "method" or "variable" holds its own exception
handler.
The class whom run this program and catches the exceptions, is manager. its in charge of taking the code file through
the stations, in the correct order, and identify errors.


=   Implementation details   =

As said in the Design part, i have splitted the program to some packages, by their "mission" of handling the code. I chose to
read the file with a scanner, that i'am are familiar with from previous projects- these is the place where I/O exceptions
can be thrown, and handle the code line by line.
So the Parser gets the lines of the code, and cut it to scopes- this may be the most important part of the program,
because it is done carefully and many of the checks on the code are done their. Each of the relevant scopes gets its
relevant lines, and "creates" it- meaning run the checks on it, part by part: the headline, the signature, the
parameters, the brackets, the "return" etc.




==  Handling s-java code errors ==

There are two types of errors: the first type is an I/O errors ,which indicates problem of getting to the file(find it,
open it, read from it etc.) for that type the output will be "2". The second type is an illegal s-java code inside the
file, for all the reasons are explained in the PDF and the output in that case will be "1"/

Both of the exceptions types are thrown independently by the different classes (in packages) of the program, by they all\
are being caught in the "manager"  that runs the program. the reason is that, whatever the exception type is, and the
matching informative message that being printed- the program should return, and stop checking the file. So it easy to
"catch" all sort of exception, in one place, and handle it the only way we chose.



== Adding new types of variables (e.g., float)? ==


We need to do some simple adjustments:

1. Add the "float" type as field in Variable.
2. Create a matching regex pattern to the FLOAT_TYPE type of variable, and add its "case" to the variable factory.


==  Required changes to support classes?  ==


There is no need for changes, only some additions.
Add a "getClassBlock" method to the Parser-  similar to the getMethod method, and create the matching regex pattern, for
detecting each part of the class(signature, fields, constructor, methods etc).
Create a factory that will get the lines of the class and handle it(search for the opening and closing brackets, detect
constructor,  collect super and sub classes and its methods and fields.. the same structure as we did with Method.
not so hard.



== Required changes to support different method types? ==


I think it will require to change the regex pattern fo method signature, and the 'return' regex pattern.
in addition, we will need to add an attribute of the return type to the method objects list that each "block" holds.
