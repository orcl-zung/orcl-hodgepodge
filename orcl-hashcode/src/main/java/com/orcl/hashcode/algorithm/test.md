```mysql-sql
-- 查询选了课程的学生人数
select sum(studentNo) from course group by studentNo;

-- 查询各科成绩最高和最低的分
select max(scoreNum), min(scoreNum) from score group by courseNo;

-- 查询至少选修两门课程的学生学号
select studentNo from score group by studentNo having count(1) >= 2;

-- 检索 “0001” 课程分数小于 60 分，按分数降序排列学生信息
select stu.studentNo, stu.studentName, stu.studentSex
from score s
         left join student stu on s.studentNo = stu.studentNo
where courseNo = '0001'
  and scoreNum < 60
order by s.scoreNum desc

-- 统计 “数学” 课程中不及格、及格、良好、优秀的分布情况
select sum(case when scoreNum < 60 then 1 end)                 as 不及格,
       sum(case when scoreNum >= 60 and score < 70 then 1 end) as 及格,
       sum(case when scoreNum >= 70 and score < 80 then 1 end) as 良好,
       sum(case when scoreNum >= 80 then 1 end)                as 及格
from score
where courseNo = (select courseNo from course where courseName = '数学')

-- 统计每科成绩均大于 80 分的同学姓名，并输出他们的总成绩排名
select stu.studentName, v.totalScore
from student stu
         left join
     (select studentNo, sum(scoreNum) totalScore
      from score
      where courseNo > 80
      group by studentNo
      having count(1) = (select count(1) from course group by courseNo)) v
     on stu.studentNo = v.studentNo
order by v.totalScore desc

```