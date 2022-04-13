data Expr = PlusExpr Expr Expr | Literal Double | MinusExpr Expr Expr |
            MultExpr Expr Expr | DivExpr Expr Expr
eval::Expr -> Double
eval (PlusExpr x y) = eval x + eval y
eval (Literal x) = x
eval (MinusExpr x y) = eval x - eval y
eval (MultExpr x y) = eval x * eval y
eval (DivExpr x y) = eval x / eval y


fac' 0 = 1
fac' n = n * fac' (n-1)