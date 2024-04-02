import { INavData } from '@coreui/angular';

export const navItems: INavData[] = [
  {
    name: 'Manager',
    title: true
  },
  {
    name: 'Pig List',
    url: 'manager/list-pigs',
    iconComponent: { name: 'cil-notes' },
  },
  {
    name: 'Sanitary Management',
    url: 'manager/sanitary-management',
    iconComponent: { name: 'cil-heart' },
  },
  {
    name: 'Register',
    url: '/registration',
    iconComponent: { name: 'cil-puzzle' },
    children: [
      {
        name: 'Register Pigs',
        url: '/registration/register-pig',
      },
    ]
  },
  {
    title: true,
    name: 'Connect with me',
    class: 'py-0'
  },
  {
    name: 'GitHub',
    url: 'https://github.com/lufecrx',
    iconComponent: { name: 'cibGithub' },
    attributes: { target: '_blank', class: '-text-dark' },
    class: 'mt-auto'
  },
  {
    name: 'LinkedIn',
    url: 'https://www.linkedin.com/in/luizfelipecg/',
    iconComponent: { name: 'cibLinkedin' },
    attributes: { target: '_blank' }
  }
];
