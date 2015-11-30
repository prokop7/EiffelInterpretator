class
	APPLICATION

inherit
	ARGUMENTS

create
	make

feature
		-- Run application
	make
		local
			INTEGER : a, c, b
			STRING : t
		do
			print("World!");
			b := 52345;
			print(b);
			t := lol();
		end

	lol : STRING
		local
			INTEGER : a
		do
			a := 1;
			print(a);
			result:= "lol";
		end

	eiffel : INTEGER
		local
		do
			result:= 0;
		end
end