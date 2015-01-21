DROP procedure IF EXISTS procedure_in1_out0;
/;
CREATE procedure procedure_in1_out0 (IN arg varchar(32))
BEGIN ATOMIC
DECLARE res varchar(64);
set res = arg; 
END;
/;