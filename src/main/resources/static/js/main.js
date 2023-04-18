// Pretendard font
(function (d) {
  var config = {
      kitId: 'nac5uey',
      scriptTimeout: 3000,
      async: true,
    },
    h = d.documentElement,
    t = setTimeout(function () {
      h.className = h.className.replace(/\bwf-loading\b/g, '') + ' wf-inactive';
    }, config.scriptTimeout),
    tk = d.createElement('script'),
    f = false,
    s = d.getElementsByTagName('script')[0],
    a;
  h.className += ' wf-loading';
  tk.src = 'https://use.typekit.net/' + config.kitId + '.js';
  tk.async = true;
  tk.onload = tk.onreadystatechange = function () {
    a = this.readyState;
    if (f || (a && a != 'complete' && a != 'loaded')) return;
    f = true;
    clearTimeout(t);
    try {
      Typekit.load(config);
    } catch (e) {}
  };
  s.parentNode.insertBefore(tk, s);
})(document);

const $text1 = document.querySelectorAll('.text1');

let isRunning = false;

function startAndEndRepeatedly() {
  if (!isRunning) {
    isRunning = true;

    const $text1 = document.querySelectorAll('.text1');
    let index = 0;

    function addClass() {
      $text1[index].classList.add('on');

      setTimeout(() => {
        $text1[index].classList.remove('on');
        index++;
        if (index < $text1.length) {
          addClass();
        } else {
          index = 0;
          isRunning = false;
        }
      }, 5000);
    }

    addClass();
  }
}

setInterval(startAndEndRepeatedly, 1000);
