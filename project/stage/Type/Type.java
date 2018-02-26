package Type;

import AST.Visitor;
public abstract class Type {
        public abstract void accept (Visitor v );
        public abstract String toString ();
        public abstract boolean equals (Type t);

        public Type getElementType() {
                return null;
        }
}
