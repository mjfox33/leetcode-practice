#!/bin/bash
project=$1
dir="$HOME/workspace/github.com/mjfox33/leetcode/${project}"
file="${dir}/code_${project}.py"
file2="${dir}/test_${project}.py"
if [ ! -d ${dir} ]; then
	mkdir -p ${dir}
	touch ${file}
	touch ${file2}
	cd ${dir}
	nvim "code_${project}.py"
	#code .
	#else
	#echo 'Directory already exists - launching code'
	#cd ${dir}
	#code .
fi
