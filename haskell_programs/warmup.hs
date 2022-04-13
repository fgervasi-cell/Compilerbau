count [] acc = acc
count (head:tail) acc = count tail (acc + 1)

map' [] _ = []
map' (head:tail) fn = fn head : (map' tail fn)