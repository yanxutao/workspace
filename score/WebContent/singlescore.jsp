
<table class="table table-bordered">
    <tr>
        <td>最低分</td>
        <td>平均分</td>
        <td>中位数</td>
        <td>最高分</td>
    </tr>
    <tr>
        <td>${singleScore.minScore}</td>
        <td>${singleScore.averageScore}</td>        
        <td>${singleScore.middleScore}</td>        
        <td>${singleScore.maxScore}</td>
    </tr>
</table>
    
<table class="table table-bordered">
    <tr>
        <td>分数段</td>
        <td>[0:60)</td>
        <td>[60:70)</td>
        <td>[70:80)</td>
        <td>[80:90)</td>
        <td>[90:100]</td>
    </tr>
    <tr>
        <td>人数</td>
        <td id="count1">${singleScore.count[0]}</td>
        <td id="count2">${singleScore.count[1]}</td>
        <td id="count3">${singleScore.count[2]}</td>
        <td id="count4">${singleScore.count[3]}</td>
        <td id="count5">${singleScore.count[4]}</td>
    </tr>
    <tr>
        <td>百分比</td>
        <td id="count1_percent">${singleScore.countPercent[0]}</td>
        <td id="count2_percent">${singleScore.countPercent[1]}</td>
        <td id="count3_percent">${singleScore.countPercent[2]}</td>
        <td id="count4_percent">${singleScore.countPercent[3]}</td>
        <td id="count5_percent">${singleScore.countPercent[4]}</td>
    </tr>
</table> 
